package com.example.technical_test_novian.utils

object Utils {
    fun getKeyByValue(map: Map<String, String>, value: String): String {
        for ((key, mapValue) in map) {
            if (mapValue == value) {
                return key
            }
        }
        return "07"
    }
}