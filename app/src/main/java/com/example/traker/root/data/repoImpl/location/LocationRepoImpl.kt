package com.example.traker.root.data.repoImpl.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.example.traker.root.data.location.hasLocationPermission
import com.example.traker.root.domain.repository.location.LocationRepo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class LocationRepoImpl (
    private val context: Context,
    private val client: FusedLocationProviderClient
) : LocationRepo {
    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(interval: Long): Flow<Location> {
        return callbackFlow {

                if(!context.hasLocationPermission()) {
                    throw LocationRepo.LocationError(message = "Missing location permission")
                }

                val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                if(!isGpsEnabled && !isNetworkEnabled) {
                    throw LocationRepo.LocationError(message = "GPS is disabled")
                }



                val request = LocationRequest.Builder(interval).build()

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        super.onLocationResult(result)
                        result.locations.lastOrNull()?.let { location ->
                            launch { send(location) }
                        }
                    }
                }

                client.requestLocationUpdates(
                    request,
                    locationCallback,
                    Looper.getMainLooper()
                )

                awaitClose {
                    client.removeLocationUpdates(locationCallback)
                }
        }

    }
}