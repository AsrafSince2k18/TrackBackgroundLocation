package com.example.traker.root.presentance.onBoard.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.traker.root.presentance.onBoard.item.OnBoardItem
import com.example.traker.root.presentance.onBoard.stateEvent.OnBoardEventSave
import com.example.traker.root.presentance.rootScreen.Screen
import com.google.accompanist.pager.HorizontalPagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardScreen(
    navHostController: NavHostController,
    event: (OnBoardEventSave)->Unit
) {

    val item = listOf(
        OnBoardItem.FirstScreen,
        OnBoardItem.SecondScreen,
        OnBoardItem.ThirdScreen,
    )

    val pagerState = rememberPagerState {
        item.size
    }

    Box(modifier = Modifier
        .fillMaxSize()){
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                color = MaterialTheme.colorScheme.surface,
                elevation = 4.dp,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.End)
            ) {
                TextButton(onClick = {
                    navHostController.navigate(Screen.SignInScreen.route)
                    navHostController.popBackStack()
                    event(OnBoardEventSave.SignIn)
                }) {
                    Text(text = "Skip")
                }
            }

            HorizontalPager(state = pagerState) {
                OnBoardSetUp(onBoardItem = item[it])
            }
            Spacer(modifier = Modifier
                .height(8.dp))

            HorizontalPagerIndicator(pagerState = pagerState,
                pageCount = pagerState.pageCount,
                indicatorShape = RectangleShape,
                indicatorWidth = 12.dp,
                indicatorHeight = 4.dp)

            Spacer(modifier = Modifier
                .height(8.dp))

            AnimButton(pagerState = pagerState) {
                navHostController.navigate(Screen.SignInScreen.route)
                navHostController.popBackStack()
                event(OnBoardEventSave.SignIn)
            }

        }
    }

}

@Composable
fun OnBoardSetUp(onBoardItem: OnBoardItem) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(onBoardItem.image))

    val progress by animateLottieCompositionAsState(composition = composition)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .size(250.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            Text(
                text = onBoardItem.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )
            Text(
                text = onBoardItem.content,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimButton(
    pagerState: PagerState,
    onclick: () -> Unit
) {

    AnimatedVisibility(visible = pagerState.currentPage==2) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            elevation = 4.dp,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
        ) {
            Button(onClick = {onclick() },
                modifier=Modifier
                    .padding(12.dp)
                    .fillMaxWidth()) {
                Text(text = "Start")
            }
        }
    }
}