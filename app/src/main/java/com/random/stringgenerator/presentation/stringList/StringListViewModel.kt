package com.random.stringgenerator.presentation.stringList

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.CancellationSignal
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.random.stringgenerator.domain.model.IAV
import com.random.stringgenerator.domain.model.RandomText
import com.random.stringgenerator.domain.model.Response
import com.random.stringgenerator.domain.repository.StringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias InsertStringResponse = Response<Unit>
typealias UpdateStringResponse = Response<Unit>
typealias DeleteStringResponse = Response<Unit>

@HiltViewModel
class StringListViewModel @Inject constructor(
    private val repo: StringRepository,
    private val contentResolver: ContentResolver
) : ViewModel() {
    val stringListState = repo.getStringList().map { list ->
        try {
            Response.Success(list)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Response.Loading
    )

    private val _insertStringState = MutableStateFlow<InsertStringResponse>(Response.Idle)
    val insertStringState: StateFlow<InsertStringResponse> = _insertStringState.asStateFlow()

    private val _deleteStringState = MutableStateFlow<DeleteStringResponse>(Response.Idle)
    val deleteStringState: StateFlow<DeleteStringResponse> = _deleteStringState.asStateFlow()

    private fun insertString(iav: IAV) = viewModelScope.launch {
        try {
            _insertStringState.value = Response.Loading
            _insertStringState.value = Response.Success(repo.insertString(iav))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun resetInsertStringState() {
        _insertStringState.value = Response.Idle
    }

    fun deleteString(iav: IAV) = viewModelScope.launch {
        try {
            _deleteStringState.value = Response.Loading
            _deleteStringState.value = Response.Success(repo.deleteString(iav))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun deleteAllStrings() = viewModelScope.launch {
        try {
            _deleteStringState.value = Response.Loading
            _deleteStringState.value = Response.Success(repo.deleteAllStrings())
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    fun resetDeleteStringState() {
        _deleteStringState.value = Response.Idle
    }

    // State to hold the list of generated strings
    var stringList = mutableStateOf<List<IAV>>(emptyList())
        private set

    // State for handling errors
    var errorMessage = mutableStateOf("")

    // Function to query the content provider and add the string
    fun generateString(length: Int) {
        viewModelScope.launch {
            try {
                val model: RandomText = getRandomStringFromContentProvider(length)
                with(model.randomText) {
                    val entity =
                        IAV(value = this.value.take(length), length = length, created = this.created)
                    insertString(entity)
                }
            } catch (e: Exception) {
                errorMessage.value = "Error generating string: ${e.message}"
            }
        }
    }

    // Function to query the content provider for a string
    private fun getRandomStringFromContentProvider(length: Int): RandomText {
        val uri =
            Uri.parse("content://com.iav.contestdataprovider/text")
        val cancellationSignal = CancellationSignal()
        val queryArgs = arrayOf(
            ContentResolver.QUERY_ARG_LIMIT +
                    " = ?$length"
        )

        val cursor: Cursor? = contentResolver.query(
            uri,
            null,
            null,
            queryArgs,
            null,
            cancellationSignal
        )

        cursor?.let {
            if (it.moveToFirst()) {
                // Get the string from the 'data' column
                val randomString = it.getString(it.getColumnIndexOrThrow("data"))
                val user = Gson().fromJson(randomString, RandomText::class.java)
                return user
            }
        }
        throw Exception("Failed to retrieve string from provider")
    }
}