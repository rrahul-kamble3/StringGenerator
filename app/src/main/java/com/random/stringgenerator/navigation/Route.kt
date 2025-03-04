package com.random.stringgenerator.navigation

import kotlinx.serialization.Serializable
import com.random.stringgenerator.domain.model.IAV

@Serializable
object StringListScreen

@Serializable
data class IAVDetails(
    val id: Int,
    val value: String,
    val length: Int,
    val created: String
)

fun IAVDetails.toIAV() = IAV(
    id = this.id,
    value = this.value,
    length = this.length,
    created = this.created,
)