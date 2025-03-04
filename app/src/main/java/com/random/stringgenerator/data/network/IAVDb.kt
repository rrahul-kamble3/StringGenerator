package com.random.stringgenerator.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.random.stringgenerator.data.dao.IAVDao
import com.random.stringgenerator.domain.model.IAV

@Database(
    entities = [IAV::class],
    version = 1,
    exportSchema = false
)
abstract class IAVDb : RoomDatabase() {
    abstract val IAVDao: IAVDao
}