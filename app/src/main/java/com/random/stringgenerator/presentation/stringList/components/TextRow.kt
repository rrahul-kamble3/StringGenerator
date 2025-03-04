package com.random.stringgenerator.presentation.stringList.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextRow(
    label: String,
    title: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 3.dp)
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.weight(0.2f),
            text = label,
            color = Color(0XFFdfff00),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier.weight(0.8f),
            text = title,
            color = Color(0XFFFFFFFF),
            style = MaterialTheme.typography.titleMedium
        )
    }
}