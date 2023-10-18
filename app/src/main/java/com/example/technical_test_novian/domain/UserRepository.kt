package com.example.technical_test_novian.domain

interface UserRepository {
    suspend fun postNewUser(UID: String,
                            uName: String,
                            uPw: String,
                            role: Int): Call<User>


    suspend fun getRoles(): Call<Roles>
}