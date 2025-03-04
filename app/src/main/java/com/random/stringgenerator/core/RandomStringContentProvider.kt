package com.random.stringgenerator.core

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri

class RandomStringContentProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        // Just for the sake of example, we return a random string.
        val randomStrings = listOf("a", "b", "c", "d", "e")
        val randomString = randomStrings.random()

        val matrixCursor = MatrixCursor(arrayOf("random_string"))
        matrixCursor.addRow(arrayOf(randomString))
        return matrixCursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}
