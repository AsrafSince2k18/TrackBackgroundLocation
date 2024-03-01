package com.example.traker.root.presentance.home.stateEvent

sealed class HomeEvent {

    data class Switch(val switch : Boolean) : HomeEvent()

}