package com.baseCode.jetpackCompose.data.repository

import android.util.Log
import com.baseCode.jetpackCompose.data.remote.DemoAPi
import com.baseCode.jetpackCompose.data.remote.dto.LoginDTO
import com.baseCode.jetpackCompose.domain.repository.DemoRepo
import com.baseCode.jetpackCompose.domain.model.LoginReqModel
import com.baseCode.jetpackCompose.domain.model.RegistrationReqModel
import com.baseCode.jetpackCompose.data.remote.dto.RegistrationDTO
import retrofit2.Response
import javax.inject.Inject

class DemoRepoImpl @Inject constructor(
    private val demoAPi: DemoAPi
) : DemoRepo {
    val TAG="DemoRepoImpl"

    override suspend fun doLogin(requestModel: LoginReqModel): Response<LoginDTO> {
        Log.e(TAG, "doLogin: ", )
        return demoAPi.login(requestModel)
    }

    override suspend fun doRegistration(requestModel: RegistrationReqModel): Response<RegistrationDTO> {
        return demoAPi.register(requestModel)
    }

}