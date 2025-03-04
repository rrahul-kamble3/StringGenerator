package com.random.stringgenerator.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.random.stringgenerator.core.IAV_TABLE
import com.random.stringgenerator.navigation.IAVDetails

@Entity(tableName = IAV_TABLE)
data class IAV(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: String,
    val length: Int,
    val created: String
)

fun IAV.toIAVDetails() = IAVDetails(
    id = this.id,
    value = this.value,
    length = this.length,
    created = this.created,
)