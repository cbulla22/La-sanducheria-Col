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

data class IngredientItem(val name: String, val imageRes: Int, var isSelected: Boolean = false)

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VegetalesScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val vegetableOptions = listOf(
        IngredientItem("Pimentón Asado", R.drawable.ic_launcher_foreground),
        IngredientItem("Cebolla Caramelizada", R.drawable.ic_launcher_foreground),
        IngredientItem("Lechuga", R.drawable.ic_launcher_foreground),
        IngredientItem("Pepinillo", R.drawable.ic_launcher_foreground),
        IngredientItem("Tomate", R.drawable.ic_launcher_foreground),
        IngredientItem("Aguacate", R.drawable.ic_launcher_foreground),
        IngredientItem("Zanahoria", R.drawable.ic_launcher_foreground),
        IngredientItem("Rúcula", R.drawable.ic_launcher_foreground)
    )

    var selectedCount by remember { mutableStateOf(0) }

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
                    IconButton(onClick = { /* TODO: Handle forward navigation */ }) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Forward")
                    }
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
                "SELECCIONA HASTA TRES VEGETALES",
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
                        if (itemIndex < vegetableOptions.size) {
                            val item = vegetableOptions[itemIndex]
                            IngredientCard(
                                item = item,
                                onSelectItem = {
                                    if (item.isSelected) {
                                        item.isSelected = false
                                        selectedCount--
                                    } else if (selectedCount < 3) {
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

            Button(
                onClick = {
                    // Navigate to the next screen (Salsas)
                    navController.navigate("salsas")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    "Sin vegetales",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Navigate to the next screen (Salsas)
                    navController.navigate("salsas")
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

@Composable
fun IngredientCard(item: IngredientItem, onSelectItem: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            Text(item.name, style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onSelectItem) {
                    Icon(
                        imageVector = if (item.isSelected) Icons.Default.Delete else Icons.Default.Add,
                        contentDescription = if (item.isSelected) "Remove" else "Add"
                    )
                }
                IconButton(onClick = onSelectItem) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = if (item.isSelected) "Remove" else "Add"
                    )
                }
            }
        }
    }
}