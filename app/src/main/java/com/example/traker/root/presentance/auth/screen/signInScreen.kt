package com.example.traker.root.presentance.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.traker.R
import com.example.traker.root.presentance.auth.stateEvent.signIn.SignInEvent
import com.example.traker.root.presentance.auth.stateEvent.signIn.SignInState
import com.example.traker.root.presentance.component.CustomTextField
import com.example.traker.root.presentance.rootScreen.Screen

@Composable
fun SignInScreen(
    state: SignInState,
    event: (SignInEvent) -> Unit,
    navHostController: NavHostController

) {
    val focusRequest = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = Unit) {
        focusRequest.requestFocus()
    }
    val keyBoardController = LocalFocusManager.current


    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.leftcirucle),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(150.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 160.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "Sign In",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )

                        Text(text = "Sign In user email and password")
                    }
                }


                Box(
                    modifier = Modifier
                        .padding(18.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomTextField(
                            query = state.email,
                            onQueryChange = {
                                event(SignInEvent.Email(it))
                            },
                            placeholder = "Email",
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Email
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    keyBoardController.moveFocus(FocusDirection.Next)
                                }
                            ),
                            isError = false,
                            modifier = Modifier
                                .focusRequester(focusRequest)
                                .fillMaxWidth()
                        )


                            Text(
                                text = state.emailError ?:"",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.Red,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .align(Alignment.End)
                            )


                        CustomTextField(
                            query = state.password,
                            onQueryChange = {
                                event(SignInEvent.Password(it))
                            },
                            placeholder = "Password",
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Email
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyBoardController.moveFocus(FocusDirection.Right)
                                }
                            ),
                            isError = false,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = state.passwordError ?:"",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Red,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(Alignment.End)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )

                        TextButton(
                            onClick = {

                            },

                            modifier = Modifier
                                .align(Alignment.End)
                        ) {
                            Text(text = "Forgot password")
                        }
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )

                        Button(
                            onClick = {
                                event(SignInEvent.SignIn)
                                navHostController.navigate(Screen.HomeScreen.route)

                            },
                            enabled = state.email.isNotBlank() && state.password.isNotBlank(),
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RectangleShape)
                        ) {
                            Text(text = "Sign in")
                        }

                    }

                }


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = buildAnnotatedString {
                            append("Or continue with ")
                            withStyle(
                                SpanStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append("Social media")
                            }
                        })


                        Spacer(
                            modifier = Modifier
                                .width(12.dp)
                        )

                        Row(
                            modifier = Modifier
                                .padding(18.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Surface(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .weight(1f),
                                shadowElevation = 4.dp,
                                shape = MaterialTheme.shapes.medium,
                                tonalElevation = 4.dp
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.google),
                                        contentDescription = "google",
                                        modifier = Modifier
                                            .size(24.dp)
                                    )

                                    Text(text = "Google",
                                        fontWeight = FontWeight.SemiBold)
                                }
                            }

                            Spacer(
                                modifier = Modifier
                                    .width(4.dp)
                            )

                            Surface(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .weight(1f),
                                shadowElevation = 4.dp,
                                shape = MaterialTheme.shapes.medium,
                                tonalElevation = 4.dp
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.facebook),
                                        contentDescription = "Facebook",
                                        modifier = Modifier
                                            .size(24.dp)
                                    )

                                    Text(text = "Facebook",
                                        fontWeight = FontWeight.SemiBold)
                                }
                            }

                        }

                        Spacer(
                            modifier = Modifier
                                .height(4.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(text = "You have no account")

                            TextButton(onClick = {
                                navHostController.navigate(Screen.SignUpScreen.route)
                            }) {
                                Text(text = "Create account",
                                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                                    fontWeight = FontWeight.SemiBold)
                            }
                        }

                    }
                }
            }
        }

    }

}

