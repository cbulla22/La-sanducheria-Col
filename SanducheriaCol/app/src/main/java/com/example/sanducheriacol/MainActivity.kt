package com.example.sanducheriacol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sanducheriaco.ui.screens.*
import com.example.sanducheriacol.ui.theme.SanducheriaColTheme
import com.example.sanducheriacol.ui.theme.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SanducheriaColTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "inicio") {
                        composable("inicio") { InicioScreen(navController) }
                        composable("login") { LoginScreen(navController) }
                        composable("home") { HomeScreen(navController) }
                        composable("menu") { MenuScreen(navController) }
                        composable("pan") { PanScreen(navController) }
                        composable("proteina") { ProteinaScreen(navController) }
                        composable("vegetales") { VegetalesScreen(navController) }
                        composable("salsas") { SalsasScreen(navController) }
                        composable("bebida") { BebidasScreen(navController) }
                        composable("cart") { CartScreen(navController) }
                        composable("payment") { PaymentScreen(navController) }
                        composable("success") { OrderConfirmationScreen(navController) }
                    }
                }
            }
        }
    }
}