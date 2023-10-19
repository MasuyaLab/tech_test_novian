package com.example.technical_test_novian.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("UID")
    val uid: String,
    @SerializedName("UName")
    val uName: String,
    @SerializedName("UPW")
    val uPw: String,
    @SerializedName("KdRole")
    val kdRole: String
)
