package com.example.technical_test_novian.data.dataSrc

import com.example.technical_test_novian.utils.Constants
import okhttp3.ResponseBody
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

    @FormUrlEncoded
    @POST(Constants.SELECT_USER_URL)
    suspend fun selectUser(
        @Field("UID") uid: String
    ): ResponseBody

    @FormUrlEncoded
    @POST(Constants.EDIT_USER_URL)
    suspend fun editUser(
        @Field("UID") uid: String,
        @Field("UName") uName: String,
        @Field("UPW") uPw: String,
        @Field("KdRole") role: String
    ): ResponseBody

    @FormUrlEncoded
    @POST(Constants.DELETE_USER_URL)
    suspend fun deleteUser(
        @Field("UID") uid: String
    ): ResponseBody

    @GET(Constants.GET_LIST_ROLES_URL)
    suspend fun getListRoles(): ResponseBody

}