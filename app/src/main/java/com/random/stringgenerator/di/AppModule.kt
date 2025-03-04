package com.random.stringgenerator.di

import android.content.ContentResolver
import android.content.Context
import androidx.room.Room
import com.random.stringgenerator.R
import com.random.stringgenerator.data.dao.IAVDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.random.stringgenerator.data.network.IAVDb
import com.random.stringgenerator.data.repository.StringRepositoryImpl
import com.random.stringgenerator.domain.repository.StringRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideIAVDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        IAVDb::class.java,
        context.resources.getString(R.string.db_name)
    ).build()

    @Provides
    fun provideIAVDao(
        IAVDb: IAVDb
    ) = IAVDb.IAVDao

    @Provides
    fun provideStringRepository(
        IAVDao: IAVDao
    ): StringRepository = StringRepositoryImpl(
        IAVDao = IAVDao
    )

    @Provides
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver {
        return context.contentResolver
    }
}