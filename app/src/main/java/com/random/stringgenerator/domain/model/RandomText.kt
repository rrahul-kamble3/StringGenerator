package com.random.stringgenerator.domain.model

data class RandomText(
    val randomText: RandomText
) {
    data class RandomText(
        val created: String,
        val length: Int,
        val value: String
    )
}