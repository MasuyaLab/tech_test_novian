package com.example.technical_test_novian.utils

object Utils {
    fun getKeyByValue(map: Map<Int, String>, value: String): Int {
        for ((key, mapValue) in map) {
            if (mapValue == value) {
                return key
            }
        }
        return 7
    }
}