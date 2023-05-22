package com.baseCode.jetpackCompose.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BaseTextComponent(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle=TextStyle(),
    color: Color = Color.Black,
    fontWeight: FontWeight? = FontWeight.Normal,
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign? = TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier,
        style=style,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}


@Preview(showBackground = true, name = "Text", group = "BaseComponent")
@Composable
fun BaseTextPreview() {
    BaseTextComponent(text = "Text")
}


@Composable
fun BaseOutlinedTextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle = LocalTextStyle.current,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    singleLine: Boolean = true,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { label?.let { Text(text = it) } },
        placeholder = { placeholder?.let { Text(text = it) } },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        textStyle = textStyle,
        interactionSource = interactionSource,
        singleLine = singleLine,
        isError = isError
    )
}


@Preview(showBackground = true, name = "TextField", group = "BaseComponent")
@Composable
fun BaseTextFieldPreview() {
    BaseOutlinedTextFieldComponent(
        "",
        placeholder = "Name",
        onValueChange = {},
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun BaseButtonComponent(
    text: String,
    onClick:()->Unit={},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary,
    shape:RoundedCornerShape= RoundedCornerShape(8)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor, contentColor = contentColor
        ), shape = shape
    ) {
        Text(text = text)
    }
}


@Preview(showBackground = true, name = "TextField", group = "BaseComponent")
@Composable
fun BaseButtonPreview() {
    BaseButtonComponent(text = "Utsav Balar")
}


