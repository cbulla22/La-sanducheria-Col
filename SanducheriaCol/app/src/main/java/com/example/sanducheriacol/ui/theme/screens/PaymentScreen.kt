package com.example.sanducheriacol.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sanducheriacol.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {
    var selectedPaymentMethod by remember { mutableStateOf("Tarjeta de Crédito") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Sandwich Icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SANDUCHERIA Col.")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Handle close action */ }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                    IconButton(onClick = { /* TODO: Handle search action */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                "Método de pago",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            PaymentOption("Tarjeta de Crédito", selectedPaymentMethod) { selectedPaymentMethod = it }
            PaymentOption("Tarjeta de Débito", selectedPaymentMethod) { selectedPaymentMethod = it }
            PaymentOption("Nequi", selectedPaymentMethod) { selectedPaymentMethod = it }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Mi carro (2)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Sandwich 1",
                    modifier = Modifier.size(60.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Sandwich 2",
                    modifier = Modifier.size(60.dp)
                )
                IconButton(onClick = { navController.navigate("cart") }) {
                    Icon(Icons.Default.ArrowForward,
                        contentDescription = "More",
                      )
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("TOTAL", style = MaterialTheme.typography.titleLarge)
                Text("$30.000", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("success") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("PAGAR AHORA", color = Color.White)
            }
        }
    }
}

@Composable
fun PaymentOption(name: String, selectedOption: String, onSelect: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = (name == selectedOption),
                onClick = { onSelect(name) }
            )
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = (name == selectedOption),
            onClick = { onSelect(name) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = name,
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(name, style = MaterialTheme.typography.bodyLarge)
    }
}