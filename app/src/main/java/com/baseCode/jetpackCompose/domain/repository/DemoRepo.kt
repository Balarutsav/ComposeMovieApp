package com.baseCode.jetpackCompose.domain.repository

import com.baseCode.jetpackCompose.data.remote.dto.LoginDTO
import com.baseCode.jetpackCompose.domain.model.LoginReqModel
import com.baseCode.jetpackCompose.domain.model.RegistrationReqModel
import com.baseCode.jetpackCompose.data.remote.dto.RegistrationDTO
import retrofit2.Response


interface DemoRepo {

    suspend fun doLogin(requestModel: LoginReqModel): Response<LoginDTO>

    suspend fun doRegistration(requestModel: RegistrationReqModel): Response<RegistrationDTO>

}