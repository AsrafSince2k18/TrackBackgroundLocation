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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.traker.R
import com.example.traker.root.presentance.auth.stateEvent.signUp.SignUpEvent
import com.example.traker.root.presentance.auth.stateEvent.signUp.SignUpState
import com.example.traker.root.presentance.component.CustomTextField

@Composable
fun SignUpScreen(
    state: SignUpState,
    event: (SignUpEvent) -> Unit,
) {


    val focusRequest = remember {
        FocusRequester()
    }


    val keyBoardController = LocalFocusManager.current

    LaunchedEffect(key1 = Unit) {
        focusRequest.requestFocus()
    }

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
                            .padding(top = 120.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "Sign Up",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )

                        Text(text = "Sign Up user name,email and password")
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
                            query = state.name,
                            onQueryChange = {
                                event(SignUpEvent.Name(it))
                            },
                            placeholder = "Name",
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Text
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
                            text = state.nameError ?:"",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Red,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(Alignment.End)
                        )

                        CustomTextField(
                            query = state.email,
                            onQueryChange = {
                                event(SignUpEvent.Email(it))
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
                                event(SignUpEvent.Password(it))
                            },
                            placeholder = "Password",
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

                        CustomTextField(
                            query = state.conformPassword,
                            onQueryChange = {
                                event(SignUpEvent.ConformPassword(it))
                            },
                            placeholder = "Conform Password",
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
                                .fillMaxWidth()
                        )
                        Text(
                            text = state.conformPasswordError ?:"",
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

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(checked = state.checkBoxTerms,
                                onCheckedChange = {
                                    event(SignUpEvent.CheckBoxTerms(it))
                                })

                            Text(text = buildAnnotatedString {
                                append("By continue you accept our ")
                                withStyle(
                                    SpanStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                ) {
                                    append("Privacy policy ")
                                }
                                append("and ")
                                withStyle(
                                    SpanStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                ) {
                                    append("Terms use")
                                }
                            })

                        }
                        Text(
                            text = state.checkBoxError ?:"",
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

                        Button(
                            onClick = {
                                event(SignUpEvent.SignInOrSignUp)
                            },
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RectangleShape)
                        ) {
                            Text(text = "Sign up")
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

                                    Text(
                                        text = "Google",
                                        fontWeight = FontWeight.SemiBold
                                    )
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

                                    Text(
                                        text = "Facebook",
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }

                        }

                    }
                }
            }
        }

    }

}


