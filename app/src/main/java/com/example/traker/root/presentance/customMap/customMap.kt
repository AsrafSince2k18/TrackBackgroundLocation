package com.example.traker.root.presentance.customMap

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.traker.root.data.user.UserLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomMap(latidue :String?, longitude:String?) {
    val context = LocalContext.current
    val openMapLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle result if needed
    }
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Button(onClick = {
            val location = Uri.parse("geo:0,0?q=$latidue,$longitude(label)")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            mapIntent.setPackage("com.google.android.apps.maps")
            openMapLauncher.launch(mapIntent)
        }) {
            Text(text = "Open Map")
        }
    }

}


@Composable
fun MapView(latitude: Double, longitude: Double) {
    val mapView = rememberMapViewWithLifecycle()

    AndroidView(
        factory = { mapView },
        update = { mapView ->
            // Initialize the map
            mapView.getMapAsync { googleMap ->
                val latLng = LatLng(latitude, longitude)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
                val markerOptions = MarkerOptions().position(latLng)
                googleMap.addMarker(markerOptions)
            }
        }
    )
}
@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            this.id= androidx.core.R.id.text
        }
    }

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle

        // Add observer for lifecycle events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                Lifecycle.Event.ON_ANY -> TODO()
            }
        }

        lifecycle.addObserver(observer)

        // Remove observer when disposing of the effect
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    return mapView
}

