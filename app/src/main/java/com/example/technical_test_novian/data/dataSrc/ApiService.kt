package com.example.technical_test_novian.data.dataSrc

import com.example.technical_test_novian.data.model.AddUserResponse
import com.example.technical_test_novian.data.model.GetRolesResponse
import com.example.technical_test_novian.data.model.GetUserResponse
import com.example.technical_test_novian.data.model.EditAndDeleteResponse
import com.example.technical_test_novian.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST(Constants.ADD_URL)
    suspend fun postNewUser(
        @Field("UID") uid: String,
        @Field("UName") uName: String,
        @Field("UPW") uPw: String,
        @Field("KdRole") role: String
    ): ResponseBody

    @GET(Constants.GET_LIST_USER_URL)
    suspend fun getListUser(): ResponseBody

    @POST(Constants.SELECT_USER_URL)
    suspend fun selectUser(uid: String): ResponseBody

    @POST(Constants.EDIT_USER_URL)
    suspend fun editUser(
        uid: String,
        uName: String,
        uPw: String,
        role: Int
    ): ResponseBody

    @POST(Constants.DELETE_USER_URL)
    suspend fun deleteUser(uid: String): ResponseBody

    @GET(Constants.GET_LIST_ROLES_URL)
    suspend fun getListRoles(): ResponseBody

}