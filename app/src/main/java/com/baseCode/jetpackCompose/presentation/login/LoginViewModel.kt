package com.baseCode.jetpackCompose.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baseCode.jetpackCompose.common.ApiResources
import com.baseCode.jetpackCompose.data.remote.dto.LoginDTO
import com.baseCode.jetpackCompose.domain.model.LoginReqModel
import com.baseCode.jetpackCompose.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {
    val TAG = "LoginViewModel"
    private val state = MutableStateFlow<ApiResources<LoginDTO>>(ApiResources.unknown())
    val mState: StateFlow<ApiResources<LoginDTO>> get() = state

    fun doLogin(loginReqModel: LoginReqModel) {

        Log.e("LoginViewModel", "doLogin: ")

        viewModelScope.launch {

            try {
                loginUseCase.invoke(loginReqModel).collect() {
                    state.value = it
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, "called ${e.message}")
            }

        }

    }
}