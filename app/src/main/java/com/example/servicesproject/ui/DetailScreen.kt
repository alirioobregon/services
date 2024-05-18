package com.example.servicesproject.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.servicesproject.data.ServiceProvider

@Composable
fun DetailScreen(service: String) {
    val context = LocalContext.current
    val names = arrayListOf("Juan perez", "pedro martinez", "alfonso suarez",
        "maria calderon", "jhon fonseca")
    val serviceProviders: List<ServiceProvider> = arrayListOf(
        ServiceProvider(names.random(), service, "321424333"),
        ServiceProvider(names.random(), service, "415344433"),
        ServiceProvider(names.random(), service, "856565665")
    )

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(serviceProviders) { serviceProvider ->
            ServiceProviderCard(serviceProvider, context)
        }
    }
}

@Composable
fun ServiceProviderCard(serviceProvider: ServiceProvider, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = serviceProvider.name, style = MaterialTheme.typography.titleMedium)
            Text(text = serviceProvider.service, style = MaterialTheme.typography.bodyMedium)
            Button(
                onClick = { makePhoneCall(context, serviceProvider.phoneNumber) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Llamar")
            }
        }
    }
}

fun makePhoneCall(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}
