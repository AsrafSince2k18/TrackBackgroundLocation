package com.example.traker.root.presentance.component

import android.annotation.SuppressLint
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    isError: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = query,
        onValueChange = {
            onQueryChange(it)
        },
        placeholder = {
            Text(text = placeholder)
        },
        isError = isError,
        keyboardOptions =keyboardOptions,
        shape = MaterialTheme.shapes.medium,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        colors = OutlinedTextFieldDefaults.colors(
            errorBorderColor = Color.Red,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            errorCursorColor = Color.Red
        ),
        modifier = modifier
    )


}