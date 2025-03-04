package com.random.stringgenerator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.random.stringgenerator.ui.theme.StringGeneratorTheme
import com.random.stringgenerator.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StringGeneratorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    NavGraph(
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}