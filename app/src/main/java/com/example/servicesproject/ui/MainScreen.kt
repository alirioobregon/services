package com.example.servicesproject.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ElectricalServices
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Nature
import androidx.compose.material.icons.filled.Plumbing
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.servicesproject.ui.theme.Purple40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigationSuccess: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hola, Alirio!", fontSize = 20.sp) },
                navigationIcon = { Icon(imageVector = Icons.Filled.Home, contentDescription = null) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Purple40,
                    titleContentColor = Color.White, navigationIconContentColor = Color.White
                )

            )
        },

        ) {
        BoxWithConstraints(Modifier.padding(it)) {
            ServiceGrid(
                services = listOf(
                    Service("Plomería", Icons.Default.Plumbing),
                    Service("Electricidad", Icons.Default.ElectricalServices),
                    Service("Carpintería", Icons.Default.Build), // Puedes cambiar los íconos según tu necesidad
                    Service("Jardinería", Icons.Default.Nature)
                ),onNavigationSuccess
            )
        }

    }
}

data class Service(val name: String, val icon: ImageVector)

@Composable
fun ServiceGrid(services: List<Service>, onNavigationSuccess: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(services) { service ->
            ServiceCard(service, onNavigationSuccess)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceCard(service: Service, onNavigationSuccess: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onClick = {
            onNavigationSuccess(service.name)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = service.icon,
                contentDescription = service.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = service.name, fontSize = 16.sp)
        }
    }
}
