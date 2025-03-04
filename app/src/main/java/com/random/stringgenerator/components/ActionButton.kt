package com.random.stringgenerator.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun ActionButton(
    onActionButtonClick: () -> Unit,
    resourceId: Int
) {
    Button(
        onClick = onActionButtonClick
    ) {
        Text(
            text = stringResource(
                id = resourceId
            )
        )
    }
}