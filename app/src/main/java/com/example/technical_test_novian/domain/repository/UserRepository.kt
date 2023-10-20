package com.example.technical_test_novian.domain.repository

import com.example.technical_test_novian.domain.model.Roles
import com.example.technical_test_novian.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerNewUser(uid: String, uName: String, uPw: String, role: String): Flow<String>

    suspend fun getListUser(): Flow<List<User>>

    suspend fun selectUser(uid: String): Flow<User>

    suspend fun editUser(
        uid: String,
        uName: String,
        uPw: String,
        role: String
    ): Flow<String>

    suspend fun deleteUser(uid: String): Flow<String>

    suspend fun getListRoles(): Flow<List<Roles>>
}