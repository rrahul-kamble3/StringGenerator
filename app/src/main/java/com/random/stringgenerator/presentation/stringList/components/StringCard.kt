package com.random.stringgenerator.presentation.stringList.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.random.stringgenerator.R
import com.random.stringgenerator.components.ActionIconButton
import com.random.stringgenerator.domain.model.IAV

@Composable
fun StringCard(
    iav: IAV,
    onDeleteString: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 8.dp,
                top = 4.dp,
                end = 8.dp,
                bottom = 4.dp
            ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors().copy(containerColor = Color(0XFF000000)),
    ) {
        Column {
            ActionIconButton(
                modifier = Modifier.align(Alignment.End),
                onActionIconButtonClick = onDeleteString,
                imageVector = Icons.Default.Delete,
                resourceId = R.string.app_name
            )

            TextRow(
                label = "String",
                title = iav.value
            )
            TextRow(
                label = "Length",
                title = iav.length.toString()
            )
            TextRow(
                label = "Date",
                title = iav.created
            )
        }
    }
}