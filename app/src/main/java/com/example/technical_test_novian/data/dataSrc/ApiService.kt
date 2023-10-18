package com.example.technical_test_novian.data.dataSrc

import com.example.technical_test_novian.data.model.RolesDto

interface ApiService {

    @GET
    suspend fun postNewUser(
        UID: String,
        uName: String,
        uPw: String,
        role: Int
    ): Call<UserDto>


    suspend fun getRoles(): Call<RolesDto>

}