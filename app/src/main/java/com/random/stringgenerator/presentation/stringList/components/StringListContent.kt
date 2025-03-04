package com.random.stringgenerator.presentation.stringList.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.random.stringgenerator.domain.model.IAV

const val NON_EXISTENT_ID = -1

@Composable
fun StringListContent(
    innerPadding: PaddingValues,
    stringList: List<IAV>,
    onDeleteString: (IAV) -> Unit,
) {
    var id by remember { mutableIntStateOf(NON_EXISTENT_ID) }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(innerPadding)
    ) {
        items(
            items = stringList,
            key = { iav ->
                iav.id
            }
        ) { iav ->
            StringCard(
                iav = iav,
                onDeleteString = {
                    onDeleteString(iav)
                    id = NON_EXISTENT_ID
                }
            )
        }
    }
}