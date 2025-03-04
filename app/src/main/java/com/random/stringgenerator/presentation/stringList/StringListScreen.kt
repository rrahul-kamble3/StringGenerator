package com.random.stringgenerator.presentation.stringList


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.random.stringgenerator.R
import com.random.stringgenerator.components.LoadingIndicator
import com.random.stringgenerator.core.showSnackBarMessage
import com.random.stringgenerator.core.showToastMessage
import com.random.stringgenerator.domain.model.IAV
import com.random.stringgenerator.domain.model.Response
import com.random.stringgenerator.presentation.stringList.components.StringListContent
import com.random.stringgenerator.presentation.stringList.components.EmptyContent
import com.random.stringgenerator.presentation.stringList.components.InsertStringAlertDialog
import com.random.stringgenerator.presentation.stringList.components.InsertStringFloatingActionButton

@Composable
fun StringListScreen(
    viewModel: StringListViewModel = hiltViewModel(),
    navigateToStringDetailsScreen: (IAV) -> Unit
) {
    val context = LocalContext.current
    val resources = context.resources
    val isLoading = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    var openInsertStringDialog by remember { mutableStateOf(false) }
    val stringListResponse by viewModel.stringListState.collectAsStateWithLifecycle()
    val insertStringResponse by viewModel.insertStringState.collectAsStateWithLifecycle()
    val deleteStringResponse by viewModel.deleteStringState.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            InsertStringFloatingActionButton(
                onInsertStringClick = {
                    openInsertStringDialog = true
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
        topBar = {
            Button(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .wrapContentWidth()
                    .padding(10.dp)
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.buttonColors().copy(containerColor = Color(0XFF000000)),
                onClick = {
                    viewModel.deleteAllStrings()
                }
            ) {
                Text(
                    modifier = Modifier.weight(0.2f),
                    text = "Delete All",
                    color = Color(0XFFdfff00),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { innerPadding ->
        when (val stringListResponse = stringListResponse) {
            is Response.Idle -> {}
            is Response.Loading -> {}
            is Response.Success -> stringListResponse.data.let { list ->
                if (list.isEmpty()) {
                    EmptyContent(
                        innerPadding = innerPadding
                    )
                } else {
                    StringListContent(
                        innerPadding = innerPadding,
                        stringList = list,
                        onDeleteString = { stringID ->
                            viewModel.deleteString(stringID)
                        },
                    )
                }
            }

            is Response.Failure -> stringListResponse.e.message?.let { errorMessage ->
                LaunchedEffect(errorMessage) {
                    showToastMessage(context, errorMessage)
                }
            }
        }
    }

    if (openInsertStringDialog) {
        InsertStringAlertDialog(
            onInsertString = { length ->
                if (length > 0) {
                    viewModel.generateString(length)
                    isLoading.value = true
                    openInsertStringDialog = false
                }
            },
            onInsertStringDialogCancel = {
                openInsertStringDialog = false
            }
        )
    }

    when (val insertStringResponse = insertStringResponse) {
        is Response.Idle -> {}
        is Response.Loading -> {}
        is Response.Success -> LaunchedEffect(Unit) {
            isLoading.value = false
            viewModel.resetInsertStringState()
        }

        is Response.Failure -> insertStringResponse.e.message?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                showToastMessage(context, errorMessage)
            }
        }
    }

    when (val deleteStringResponse = deleteStringResponse) {
        is Response.Idle -> {}
        is Response.Loading -> {}
        is Response.Success -> LaunchedEffect(Unit) {
            showSnackBarMessage(
                coroutineScope = coroutineScope,
                snackBarHostState = snackBarHostState,
                message = resources.getString(R.string.deleted)
            )
            viewModel.resetDeleteStringState()
        }

        is Response.Failure -> deleteStringResponse.e.message?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                showToastMessage(context, errorMessage)
            }
        }
    }
}