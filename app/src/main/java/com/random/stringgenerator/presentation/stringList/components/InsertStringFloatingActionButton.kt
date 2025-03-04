package com.random.stringgenerator.presentation.stringList.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.random.stringgenerator.R

@Composable
fun InsertStringFloatingActionButton(
    onInsertStringClick: () -> Unit
) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = onInsertStringClick
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(
                id = R.string.app_name
            )
        )
    }
}