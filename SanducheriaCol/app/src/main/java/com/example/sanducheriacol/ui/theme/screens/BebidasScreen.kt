package com.example.sanducheriacol.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BebidasScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val bebidaOptions = listOf(
        IngredientItem("Agua", R.drawable.bebida1),
        IngredientItem("Cerveza", R.drawable.cerveza),
        IngredientItem("Coca-Cola", R.drawable.bebida3),
        IngredientItem("Sprite", R.drawable.bebida4),
        IngredientItem("Jugo de Naranja", R.drawable.jugo_naranja),
        IngredientItem("Limonada", R.drawable.limonada),
        IngredientItem("Té Helado", R.drawable.te_helado),
        IngredientItem("Café", R.drawable.cafe)
    )

    var selectedCount by remember { mutableStateOf(0) }

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
                "POR ÚLTIMO LAS BEBIDAS",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPager(
                count = 2,
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(4) { index ->
                        val itemIndex = page * 4 + index
                        if (itemIndex < bebidaOptions.size) {
                            val item = bebidaOptions[itemIndex]
                            IngredientCard(
                                item = item,
                                onSelectItem = {
                                    if (item.isSelected) {
                                        item.isSelected = false
                                        selectedCount--
                                    } else if (selectedCount < 1) {
                                        item.isSelected = true
                                        selectedCount++
                                    }
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
            )

            Text(
                "Sin bebida.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Button(
                onClick = {
                    // Navigate to the next screen (e.g., order summary or checkout)
                    navController.navigate("cart")
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