package com.random.stringgenerator.data.repository

import com.random.stringgenerator.data.dao.IAVDao
import com.random.stringgenerator.domain.model.IAV
import com.random.stringgenerator.domain.repository.StringRepository

class StringRepositoryImpl(
    private val IAVDao: IAVDao
) : StringRepository {
    override fun getStringList() = IAVDao.getStringList()

    override suspend fun getStringByID(id: Int) = IAVDao.getStringById(id)

    override suspend fun insertString(iav: IAV) = IAVDao.insertString(iav)

    override suspend fun updateString(iav: IAV) = IAVDao.updateString(iav)

    override suspend fun deleteString(iav: IAV) = IAVDao.deleteString(iav)

    override suspend fun deleteAllStrings() = IAVDao.deleteAllStrings()
}