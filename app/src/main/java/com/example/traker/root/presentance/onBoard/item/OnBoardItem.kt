package com.example.traker.root.presentance.onBoard.item

import com.example.traker.R

sealed class OnBoardItem(
    val title : String,
    val content : String,
    val image : Int
) {

    object FirstScreen :OnBoardItem(
        title = "Find Location",
        content = "Find your current location in the background.",
        image = R.raw.location
    )

    object SecondScreen :OnBoardItem(
        title = "Save Location",
        content = "Save your location every 15 minutes.",
        image = R.raw.location
    )

    object ThirdScreen :OnBoardItem(
        title = "View Location",
        content = "View your saved locations.",
        image = R.raw.location
    )

}