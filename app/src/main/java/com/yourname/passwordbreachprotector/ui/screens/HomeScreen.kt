package com.octane.pbd.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.octane.pbd.viewmodel.CheckViewModel

@Composable
fun CheckScreen(viewModel: CheckViewModel) {
    val state by viewModel.state.collectAsState()

    var pwd by remember { mutableStateOf("") }
    var show by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Password Breach Detector") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = pwd,
                onValueChange = { pwd = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = if (show) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = { show = !show }) {
                        Text(if (show) "HIDE" else "SHOW")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.check(pwd) },
                enabled = !state.loading && pwd.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (state.loading) "Checking..." else "Check breach")
            }

            when {
                state.error != null -> {
                    AssistChip(onClick = { viewModel.clearError() }, label = { Text(state.error!!) })
                }
                state.result != null -> {
                    ResultCard(
                        count = state.result!!.count
                    )
                }
            }
        }
    }
}

@Composable
private fun ResultCard(count: Int) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = if (count > 0) "⚠️ Found in $count breaches"
                else "✅ Not found in known breaches",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = if (count > 0)
                    "Change this password everywhere you used it and consider a manager + MFA."
                else
                    "Good sign. Still use unique passwords per site."
            )
        }
    }
}
