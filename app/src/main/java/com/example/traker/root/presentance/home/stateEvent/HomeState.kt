package com.example.traker.root.presentance.home.stateEvent

import android.location.Location
import com.example.traker.root.data.user.UserLocation

data class HomeState(

    val switch : Boolean = false,
    val interval : Long=10000L,

    val locationItem : Location ?= null,

    var userLocationList: List<UserLocation> = emptyList(),

)
