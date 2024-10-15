package com.example.sanducheriacol.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sanducheriacol.R
import com.example.sanducheriacol.viewmodel.SandwichViewModel
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PanScreen(navController: NavController, viewModel: SandwichViewModel) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val breadOptions = listOf(
        "Pan Blanco" to R.drawable.whitebread,
        "Pan Integral" to R.drawable.whole_bread,
        "Pan de Centeno" to R.drawable.rye_bread
    )

    var selectedBread by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.logo_no_background),
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "PRIMERO ELIGE EL PAN",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPager(
                count = breadOptions.size,
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = breadOptions[page].second),
                        contentDescription = breadOptions[page].first,
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        breadOptions[page].first,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    selectedBread = breadOptions[pagerState.currentPage].first
                    viewModel.startNewSandwich(selectedBread)
                    navController.navigate("proteina")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("AGREGAR", color = Color.White)
            }
        }
    }
}