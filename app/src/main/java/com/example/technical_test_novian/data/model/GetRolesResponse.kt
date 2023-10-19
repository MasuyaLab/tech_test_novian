package com.example.technical_test_novian.data.model

import com.google.gson.annotations.SerializedName

data class GetRolesResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: List<RolesDto>
)
