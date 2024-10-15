package com.example.sanducheriacol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sanducheriaco.ui.screens.LoginScreen
import com.example.sanducheriacol.model.SandwichDatabase
import com.example.sanducheriacol.model.SandwichRepositoryImpl
import com.example.sanducheriacol.ui.screens.PanScreen
import com.example.sanducheriacol.ui.theme.SanducheriaColTheme
import com.example.sanducheriacol.ui.theme.screens.BebidasScreen
import com.example.sanducheriacol.ui.theme.screens.CartScreen
import com.example.sanducheriacol.ui.theme.screens.HomeScreen
import com.example.sanducheriacol.ui.theme.screens.InicioScreen
import com.example.sanducheriacol.ui.theme.screens.MenuScreen
import com.example.sanducheriacol.ui.theme.screens.OrderConfirmationScreen
import com.example.sanducheriacol.ui.theme.screens.PaymentScreen
import com.example.sanducheriacol.ui.theme.screens.ProteinaScreen
import com.example.sanducheriacol.ui.theme.screens.SalsasScreen
import com.example.sanducheriacol.ui.theme.screens.VegetalesScreen
import com.example.sanducheriacol.viewmodel.SandwichViewModel
import com.example.sanducheriacol.viewmodel.SandwichViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create the database instance
        val database = SandwichDatabase.getDatabase(applicationContext)

        // Create the repository instance with the sandwichDao
        val sandwichRepository = SandwichRepositoryImpl(database.sandwichDao())

        // Create the ViewModelFactory
        val viewModelFactory = SandwichViewModelFactory(sandwichRepository)

        // Create the ViewModel
        val sandwichViewModel = ViewModelProvider(this, viewModelFactory)[SandwichViewModel::class.java]

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
                        composable("pan") { PanScreen(navController, sandwichViewModel) }
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