package com.baseCode.jetpackCompose.domain.use_case

import android.util.Log
import com.baseCode.jetpackCompose.common.ApiResources
import com.baseCode.jetpackCompose.data.remote.BaseDataSource
import com.baseCode.jetpackCompose.data.remote.dto.LoginDTO
import com.baseCode.jetpackCompose.domain.model.LoginReqModel
import com.baseCode.jetpackCompose.domain.repository.DemoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val demoRepo: DemoRepo, private val baseDataSource: BaseDataSource
) {
    val TAG = "Login UseCase"

    operator fun invoke(loginReqModel: LoginReqModel): Flow<ApiResources<LoginDTO>> = flow {
        try {
            Log.e(TAG, "invoke: called")
            emit(ApiResources.loading())
            val data = demoRepo.doLogin(loginReqModel)
            emit(baseDataSource.getResult { data })
        } catch (e: HttpException) {
            Log.e(TAG, "invoke: ${e.message}")
            emit(ApiResources.error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            Log.e(TAG, "invoke: ${e.message}")
            emit(ApiResources.error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            Log.e(TAG, "invoke: ${e.message}")
            emit(ApiResources.error(e.message))
        }
    }

}