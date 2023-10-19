package com.example.technical_test_novian.utils

sealed class DataState<out T> {
    object Loading: DataState<Nothing>()
    class Success<T>(val data: T): DataState<T>()
    class Failure(val message: String): DataState<Nothing>()
}