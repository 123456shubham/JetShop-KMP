package org.example.jetshop.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun LoginPromptDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onLoginClick: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() }, // Ensure dismiss updates state
            title = { Text("Login Required") },
            text = { Text("Sign in to explore and enjoy all the features of our app.") },
            confirmButton = {
                Button(onClick = { onLoginClick() }) {
                    Text("Login",)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancel")
                }
            }
        )
    }
}
