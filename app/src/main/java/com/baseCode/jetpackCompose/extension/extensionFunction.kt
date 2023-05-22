package com.baseCode.jetpackCompose.extension

import android.widget.EditText



fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.checkIsEmpty(): Boolean {
    return this.trim().isEmpty()
}