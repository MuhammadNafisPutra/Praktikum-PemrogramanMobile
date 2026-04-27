package com.example.sahamapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sahamapp.data.Stock

@Composable
fun StockListItem(
    stock: Stock,
    modifier: Modifier = Modifier,
    onNavigateToDetail: () -> Unit
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
            .width(300.dp)
            .height(380.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Image(
                    painter = painterResource(id = stock.imageRes),
                    contentDescription = stringResource(id = stock.nameRes),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = stringResource(id = stock.nameRes), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(text = stringResource(id = R.string.label_year) + stringResource(id = stock.yearRes), style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(id = R.string.label_profil), fontWeight = FontWeight.Bold)
                    Text(
                        text = stringResource(id = stock.descRes),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(stock.webLink))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.btn_web))
                }

                Button(
                    onClick = onNavigateToDetail,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.btn_detail))
                }
            }
        }
    }
}