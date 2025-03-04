package com.random.stringgenerator.core

import android.content.Context
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

const val IAV_TABLE = "iav_table"

fun showToastMessage(
    context: Context,
    message: String
) = makeText(context, message, LENGTH_LONG).show()

fun showSnackBarMessage(
    coroutineScope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
    message: String
) = coroutineScope.launch {
    snackBarHostState.showSnackbar(message)
}