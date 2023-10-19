package com.example.technical_test_novian.data.repo_impl

import android.util.Log
import com.example.technical_test_novian.data.dataSrc.ApiService
import com.example.technical_test_novian.data.mapper.toDomain
import com.example.technical_test_novian.data.model.AddUserResponse
import com.example.technical_test_novian.data.model.EditAndDeleteResponse
import com.example.technical_test_novian.data.model.GetRolesResponse
import com.example.technical_test_novian.data.model.GetUserResponse
import com.example.technical_test_novian.domain.model.Roles
import com.example.technical_test_novian.domain.model.User
import com.example.technical_test_novian.domain.repository.UserRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import okhttp3.internal.wait

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val gson: Gson
): UserRepository {
    override suspend fun registerNewUser(uid: String, uName: String, uPw: String, role: String): Flow<String> {
        return flow {
            val response = apiService.postNewUser(uid, uName, uPw, role).string()
            val jsonResponse = gson.fromJson(response, AddUserResponse::class.java)

            emit(jsonResponse.result[0].response)
        }
    }

    override suspend fun getListUser(): Flow<List<User>> {
        return flow {
            val response = apiService.getListUser().string()
            val jsonResponse = gson.fromJson(response, GetUserResponse::class.java)
            emit(jsonResponse.result.map { it.toDomain() })
        }
    }

    override suspend fun selectUser(uid: String): Flow<User> {
        return flow {
            val response = apiService.selectUser(uid).string()
            val jsonResponse = gson.fromJson(response, GetUserResponse::class.java)
            emit(jsonResponse.result[0].toDomain())
        }
    }

    override suspend fun editUser(uid: String, uName: String, uPw: String, role: Int): Flow<String> {
        return flow {
            val response = apiService.editUser(uid, uName, uPw, role).string()
            val jsonResponse = gson.fromJson(response, EditAndDeleteResponse::class.java)

            emit(jsonResponse.result[0].response)
        }
    }

    override suspend fun deleteUser(uid: String): Flow<String> {
        return flow {
            val response = apiService.deleteUser(uid).string()
            val jsonResponse = gson.fromJson(response, EditAndDeleteResponse::class.java)
            emit(jsonResponse.result[0].response)
        }
    }

    override suspend fun getListRoles(): Flow<List<Roles>> {
        return flow {
            val response = apiService.getListRoles().string()
            val jsonResponse = gson.fromJson(response, GetRolesResponse::class.java)
            emit(jsonResponse.result.map { it.toDomain() })
        }
    }

}