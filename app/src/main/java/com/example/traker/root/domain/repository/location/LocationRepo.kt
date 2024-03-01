package com.example.traker.root.domain.repository.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepo {
    fun getCurrentLocation(interval: Long): Flow<Location>
    class LocationError(message: String): Exception()

}
