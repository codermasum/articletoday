package com.example.helloarticles.utils

sealed class Results<out T : Any> {
    data class Success<out T : Any>(val data: T) : Results<T>()
    data class Error(val exception: Exception) : Results<Nothing>()
    object InProgress : Results<Nothing>()

    val extractData: T?
        get() = when (this) {
            is Success -> data
            is Error -> null
            is InProgress -> null
        }
}