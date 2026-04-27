package com.example.sahamapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.sahamapp.ui.theme.SahamAppTheme // Import tema buatanmu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SahamAppTheme {
                val navController = rememberNavController()
                SahamNavGraph(navController = navController)
            }
        }
    }
}