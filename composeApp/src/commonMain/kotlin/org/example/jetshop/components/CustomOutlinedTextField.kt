package org.example.jetshop.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.lock
import jetshop.composeapp.generated.resources.montserrat_medium
import jetshop.composeapp.generated.resources.visibility
import jetshop.composeapp.generated.resources.visible
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: DrawableResource? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    isEnabled: Boolean = true
) {
    val customFont = FontFamily(Font(Res.font.montserrat_medium)) // Replace with your font resource

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                fontFamily = customFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        },
        textStyle = TextStyle(
            fontFamily = customFont,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        ),
        leadingIcon = leadingIcon?.let {
            { Icon(painter = painterResource(it), contentDescription = label, modifier= Modifier.size(18.dp)) }
        },

        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier.fillMaxWidth(),
        singleLine = singleLine,
        enabled = isEnabled, // ✅ Controlled by state


    )
}

@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Password",
    modifier: Modifier = Modifier,
    iserror: Boolean
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val customFont = FontFamily(Font(Res.font.montserrat_medium)) // Replace with your font resource

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                fontFamily = customFont,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        },
        textStyle = TextStyle(
            fontFamily = customFont,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        ),
        leadingIcon = { Icon(painterResource(Res.drawable.lock), contentDescription = "Password",modifier.size(24.dp)) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val iconRes = if (passwordVisible) Res.drawable.visible else Res.drawable.visibility
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = painterResource(iconRes), contentDescription = "Toggle Password Visibility",modifier.size(24.dp))
            }
        },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        isError = iserror
    )
}
