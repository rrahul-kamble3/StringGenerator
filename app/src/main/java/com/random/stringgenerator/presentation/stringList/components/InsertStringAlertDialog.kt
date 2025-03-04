package com.random.stringgenerator.presentation.stringList.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.random.stringgenerator.R
import com.random.stringgenerator.components.ActionButton

const val EMPTY_STRING = ""

@Composable
fun InsertStringAlertDialog(
    onInsertString: (length: Int) -> Unit,
    onInsertStringDialogCancel: () -> Unit,
) {
    var title by remember { mutableStateOf(EMPTY_STRING) }
    var stringLength by remember { mutableStateOf(0) }

    AlertDialog(
        onDismissRequest = onInsertStringDialogCancel,
        title = {
            Text(
                text = stringResource(
                    id = R.string.enter_length
                )
            )
        },
        text = {
            Column {
                InputField(
                    title = title,
                ) { length ->
                    stringLength = length
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }
        },
        confirmButton = {
            ActionButton(
                onActionButtonClick = {
                    onInsertString(
                        stringLength
                    )
                    onInsertStringDialogCancel()
                },
                resourceId = R.string.generate_string
            )
        },
        dismissButton = {
            ActionButton(
                onActionButtonClick = onInsertStringDialogCancel,
                resourceId = R.string.cancel
            )
        }
    )
}