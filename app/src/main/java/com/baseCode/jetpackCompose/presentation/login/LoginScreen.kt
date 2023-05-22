package com.baseCode.jetpackCompose.presentation.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection.Companion.In
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Copy
import androidx.compose.ui.layout.ContentScale.Companion.Inside
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baseCode.jetpackCompose.R
import com.baseCode.jetpackCompose.common.ApiResources
import com.baseCode.jetpackCompose.domain.model.LoginReqModel
import com.baseCode.jetpackCompose.extension.checkIsEmpty
import com.baseCode.jetpackCompose.extension.isValidEmail
import com.baseCode.jetpackCompose.presentation.components.BaseButtonComponent
import com.baseCode.jetpackCompose.presentation.components.BaseOutlinedTextFieldComponent
import com.baseCode.jetpackCompose.presentation.components.BaseTextComponent
import com.baseCode.jetpackCompose.presentation.destinations.ProfileScreenDestination
import com.baseCode.jetpackCompose.ui.theme.Purple500
import com.baseCode.jetpackCompose.ui.theme.Purple700
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator, loginViewModel: LoginViewModel = hiltViewModel()
) {
    LoginContent(navigator, loginViewModel)

}

@Composable
fun LoginContent(navigator: DestinationsNavigator? = null, loginViewModel: LoginViewModel?) {

    val TAG = "Login Screen"
    var email by remember { mutableStateOf(("Developer5@gmail.com")) }
    var password by rememberSaveable { mutableStateOf("123456") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isShowProgressDialog by remember { mutableStateOf(false) }
    val loginResponse by loginViewModel!!.mState.collectAsState()
    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BaseTextComponent(
            text = stringResource(R.string.lbl_welcome_back),
            color = Purple700,
            modifier = Modifier.padding(bottom = 15.dp),
            style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold)
        )
        BaseTextComponent(
            text = stringResource(R.string.lbl_signin_and_continue),
            style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Purple500),
        )
        BaseOutlinedTextFieldComponent(
            value = email,
            onValueChange = {
                email = it
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
            ),
            placeholder = stringResource(R.string.lbl_email_address),
        )

        BaseOutlinedTextFieldComponent(
            value = password,
            onValueChange = {
                password = it
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) R.drawable.visibility
                else R.drawable.visibility_off

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painterResource(id = image), description)
                }
            },
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
            ),
            placeholder = stringResource(id = R.string.lbl_password),


            )

        BaseButtonComponent(
            text = stringResource(R.string.lbl_login), onClick = {
                if (loginViewModel != null) {
                    validateLoginDetails(email, password, loginViewModel, onInvalidate = {
                        Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()

                    })
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        )
    }

    Log.e(TAG, "LoginScreen: Api status ${loginResponse.status.name}")
    when (loginResponse.status) {
        ApiResources.Status.LOADING -> {
            isShowProgressDialog = true
        }
        ApiResources.Status.SUCCESS -> {
            isShowProgressDialog = false

            navigator?.navigate(ProfileScreenDestination)


        }
        ApiResources.Status.ERROR -> {
            isShowProgressDialog = false
            loginResponse.message?.let { showToastMessage(context, it) }
        }
        else -> {

        }
    }
    if (isShowProgressDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }






}

fun validateLoginDetails(
    email: String,
    password: String,
    viewModel: LoginViewModel,
    onInvalidate: (Int) -> Unit,
) {
    when {
        email.checkIsEmpty() -> {

            onInvalidate.invoke(R.string.msg_enter_email)
            return
        }

        !email.isValidEmail() -> {

            onInvalidate.invoke(R.string.msg_valid_email)

            return

        }

        password.length < 6 -> {
            onInvalidate.invoke(R.string.msg_password_minimum_6_chars)

            return
        }

        password.checkIsEmpty() -> {
            onInvalidate.invoke(R.string.msg_password_empty)
            return
        }
        else -> {
            viewModel.doLogin(LoginReqModel(email = email, password = password))
        }

    }


}

fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    LoginContent(loginViewModel = null)
}
