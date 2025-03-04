package com.random.stringgenerator.domain.repository

import kotlinx.coroutines.flow.Flow
import com.random.stringgenerator.domain.model.IAV

interface StringRepository {
    fun getStringList(): Flow<List<IAV>>

    suspend fun getStringByID(id: Int): IAV?

    suspend fun insertString(iav: IAV)

    suspend fun updateString(iav: IAV)

    suspend fun deleteString(iav: IAV)

    suspend fun deleteAllStrings()
}