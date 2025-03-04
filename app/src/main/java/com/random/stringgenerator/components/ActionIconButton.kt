package com.random.stringgenerator.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
fun ActionIconButton(
    modifier: Modifier = Modifier,
    onActionIconButtonClick: () -> Unit,
    imageVector: ImageVector,
    resourceId: Int
) {
    IconButton(
        modifier = modifier,
        onClick = onActionIconButtonClick
    ) {
        Icon(
            imageVector = imageVector,
            tint = Color(0xFFFC0808),
            contentDescription = stringResource(
                id = resourceId
            )
        )
    }
}