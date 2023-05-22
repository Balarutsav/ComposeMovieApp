package com.baseCode.jetpackCompose.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("data")
    val `data`: Data?=null
) : BaseDTO(){
    data class Data(
        @SerializedName("Email")
        val email: String,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("Name")
        val name: String,
        @SerializedName("Token")
        val token: String
    )
}