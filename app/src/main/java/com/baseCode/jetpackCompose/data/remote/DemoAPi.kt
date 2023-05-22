package com.baseCode.jetpackCompose.data.remote

import com.baseCode.jetpackCompose.common.Constants.LOGIN_END_POINT
import com.baseCode.jetpackCompose.common.Constants.REGISTER_END_POINT
import com.baseCode.jetpackCompose.data.remote.dto.LoginDTO
import com.baseCode.jetpackCompose.domain.model.LoginReqModel
import com.baseCode.jetpackCompose.domain.model.RegistrationReqModel
import com.baseCode.jetpackCompose.data.remote.dto.RegistrationDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface DemoAPi{

    @POST(LOGIN_END_POINT)
    suspend fun login(@Body request: LoginReqModel): Response<LoginDTO>

    @POST(REGISTER_END_POINT)
    suspend fun register(@Body request: RegistrationReqModel): Response<RegistrationDTO>

}