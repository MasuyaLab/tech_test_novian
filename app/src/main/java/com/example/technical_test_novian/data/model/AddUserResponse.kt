package com.example.technical_test_novian.data.model

import com.google.gson.annotations.SerializedName

/** AI: Need to change this to abstraction data class
 *  Happens with other class with prefix ...Response as well
 * */
data class AddUserResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("result")
    val result: List<Response>
)
