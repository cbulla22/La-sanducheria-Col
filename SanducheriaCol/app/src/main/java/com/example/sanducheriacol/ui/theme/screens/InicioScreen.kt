package com.example.sanducheriacol.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sanducheriacol.R


@Composable
fun InicioScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo_no_background),
            contentDescription = "Sandwich Icon",
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "SANDUCHERIA Col.",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = "Bienvenido...",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "La cuestión es de hambre,\nasí que comencemos.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        IconButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .padding(top = 32.dp)
                .size(48.dp)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
        ) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Start")
        }
    }
}