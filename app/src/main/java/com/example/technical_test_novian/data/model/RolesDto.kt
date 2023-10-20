package com.example.technical_test_novian.data.model

import com.google.gson.annotations.SerializedName

data class RolesDto(
    @SerializedName("KdRole")
    val kdRoles: String,
    @SerializedName("NmRole")
    val nmRole: String
)