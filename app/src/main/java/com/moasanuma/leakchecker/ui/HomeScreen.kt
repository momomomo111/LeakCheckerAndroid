package com.moasanuma.leakchecker.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moasanuma.leakchecker.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    var pass by remember { mutableStateOf("") }
    var errorPass by remember { mutableStateOf(false) }
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.search_pass_input_msg),
                style = typography.h5
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                value = pass,
                onValueChange = { pass = it },
                singleLine = true,
                label = { Text(stringResource(R.string.password)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(errorPass) {
                Text(
                    stringResource(R.string.pass_error_msg),
                    color = MaterialTheme.colors.error,
                    style = typography.h6,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    errorPass = !emptyCheck(pass)
                    if (!errorPass) navController.navigate("result/$pass")
                }
            ) {
                Text(
                    text = stringResource(R.string.search),
                    style = typography.h5
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = stringResource(R.string.pass_encryption_msg),
                style = typography.h6,
                color = Color.Gray
            )
        }
    }
}

private fun emptyCheck(pass: String): Boolean {
    return pass != ""
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}
