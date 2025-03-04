package com.random.stringgenerator.presentation.stringList.components


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.text.isDigitsOnly

@Composable
fun InputField(
    title: String,
    length: (Int) -> Unit,
) {
    var title by remember {
        mutableStateOf(
            TextFieldValue(
                text = title,
                selection = TextRange(title.length)
            )
        )
    }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField(
        modifier = Modifier.focusRequester(focusRequester),
        value = title,
        onValueChange = { newTitle ->
            if (newTitle.text.isDigitsOnly()) {
                title = newTitle
                length(newTitle.text.toInt() ?: 0)
            }
        },
        label = { Text("Enter a string length") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
    )
}