package com.example.helloarticles.model.network

import com.example.helloarticles.utils.Results

abstract class BaseRepository {
    companion object {
        private const val UNAUTHORIZED = "Unauthorized"
        private const val NOT_FOUND = "Not found"
        const val SOMETHING_WRONG = "Something went wrong"

        fun <T : Any> handleSuccess(data: T): Results<T> {
            return Results.Success(data)
        }

        fun <T : Any> handleException(code: Int): Results<T> {
            val exception = getErrorMessage(code)
            return Results.Error(Exception(exception))
        }

        private fun getErrorMessage(httpCode: Int): String {
            return when (httpCode) {
                401 -> UNAUTHORIZED
                404 -> NOT_FOUND
                else -> SOMETHING_WRONG
            }
        }
    }
}