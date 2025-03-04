package com.random.stringgenerator.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.random.stringgenerator.core.IAV_TABLE
import com.random.stringgenerator.domain.model.IAV
import kotlinx.coroutines.flow.Flow

@Dao
interface IAVDao {
    @Query("SELECT * FROM $IAV_TABLE ORDER BY id ASC")
    fun getStringList(): Flow<List<IAV>>

    @Query("SELECT * FROM $IAV_TABLE WHERE id = :id")
    suspend fun getStringById(id: Int): IAV

    @Query("delete FROM $IAV_TABLE")
    suspend fun deleteAllStrings()

    @Insert
    suspend fun insertString(iav: IAV)

    @Update
    suspend fun updateString(iav: IAV)

    @Delete
    suspend fun deleteString(iav: IAV)
}