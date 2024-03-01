package com.example.traker.root.presentance.home.screen.homeScreen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOff
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.traker.root.data.location.LocationServies
import com.example.traker.root.data.user.UserLocation
import com.example.traker.root.presentance.home.stateEvent.HomeEvent
import com.example.traker.root.presentance.home.stateEvent.HomeState
import com.example.traker.root.presentance.rootScreen.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeState: HomeState,
    event: (HomeEvent) -> Unit,
    navHostController: NavHostController
) {
    val applicationContext = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Tracker")
            },
                actions = {
                    Switch(
                        checked = homeState.switch,
                        onCheckedChange = {
                            event(HomeEvent.Switch(it))
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.primary,
                            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        thumbContent = if (homeState.switch) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.LocationOn,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                )
                                Intent(applicationContext, LocationServies::class.java).apply {
                                    action = LocationServies.ACTION_START
                                    applicationContext.startService(this)
                                }


                            }
                        } else {
                            {
                                Icon(
                                    imageVector = Icons.Filled.LocationOff,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                )
                                Intent(applicationContext, LocationServies::class.java).apply {
                                    action = LocationServies.ACTION_STOP
                                    applicationContext.startService(this)
                                }
                            }
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    state = LazyListState(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(homeState.userLocationList) {userLocation->
                        UserItemShow(userLocation, onItemClick = {
                            navHostController.navigate(Screen.CustomMapView.route+"/${userLocation.userLocationList?.latitude}"+"/${userLocation.userLocationList?.longitude}")
                        })
                    }
                }
            }
        }
    }
}



@Composable
fun UserItemShow(location: UserLocation,onItemClick : () -> Unit) {

    val latitude = location.userLocationList?.latitude
    val longitude = location.userLocationList?.longitude
    val dateTime = location.userLocationList?.dateTime
    Surface(
        onClick = {
            onItemClick()
        },
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
        contentColor = MaterialTheme.colorScheme.primary
    ) {

        Column(
            modifier = Modifier
                .padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Sharp.LocationOn,
                    contentDescription = ""
                )
                Text(text = "Location ($latitude,$longitude)")
            }
            Divider()

            Text(
                text = buildAnnotatedString {
                    append("Date and Time: ")
                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(dateTime)
                    }
                }, modifier = Modifier
                    .align(Alignment.End)
            )
        }

    }
}
