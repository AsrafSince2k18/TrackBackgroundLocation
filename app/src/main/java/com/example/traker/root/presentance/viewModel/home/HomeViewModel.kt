package com.example.traker.root.presentance.viewModel.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traker.root.data.user.UserData
import com.example.traker.root.data.user.UserLocation
import com.example.traker.root.domain.repository.databaseRepo.RealmDatabaseRepo
import com.example.traker.root.domain.repository.location.LocationRepo
import com.example.traker.root.presentance.home.stateEvent.HomeEvent
import com.example.traker.root.presentance.home.stateEvent.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationRepo: LocationRepo,
    private val realmDatabaseRepo: RealmDatabaseRepo
) : ViewModel() {


    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.Switch -> {
                _homeState.update {
                    it.copy(switch = event.switch)
                }
            }
        }
    }

    init {
        insertData()
    }
    init {
        viewModelScope.launch {
            realmDatabaseRepo.getAllData().collect{user->
                _homeState.update {
                    it.copy(userLocationList = user)
                }
            }
        }
    }




    @RequiresApi(Build.VERSION_CODES.O)
    fun insertData(){
        viewModelScope.launch {
            locationRepo.getCurrentLocation(interval = homeState.value.interval)
                .collect { location ->
                _homeState.update {
                    it.copy(locationItem = location)
                }
                while (true){
                    insertData(location.latitude,location.longitude)
                    delay(15 * 60 * 1000)
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertData(i : Double, l : Double){
        viewModelScope.launch {
            val currentDateTime = LocalDateTime.now()
            val formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
            val item = UserData().apply {
                this.latitude=i.toString()
                this.longitude=l.toString()
                this.dateTime=formattedDateTime
            }
            realmDatabaseRepo.insertLocation(
                userLocation = UserLocation().apply {
                    userLocationList=item
                }
            )
        }
    }



}