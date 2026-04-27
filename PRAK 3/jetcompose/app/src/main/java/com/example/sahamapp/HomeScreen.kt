package com.example.sahamapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sahamapp.data.getStockList

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val sharedPref = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)

        if (isFirstTime) {
            Toast.makeText(context, context.getString(R.string.first_time_msg), Toast.LENGTH_LONG).show()
            sharedPref.edit().putBoolean("isFirstTime", false).apply()
        }
    }

    val stockList = getStockList()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // 1. Judul untuk bagian Horizontal
        item {
            Text(
                text = "SAHAM BANK INDONESIA",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(stockList.size) { index ->
                    StockListItem(
                        stock = stockList[index],
                        onNavigateToDetail = { navController.navigate("detail/$index") }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "SAHAM BANK INDONESIA",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        items(stockList.size) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                StockListItem(
                    stock = stockList[index],
                    onNavigateToDetail = { navController.navigate("detail/$index") }
                )
            }
        }
    }
}