package com.moasanuma.leakchecker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moasanuma.leakchecker.viewmodel.PassViewModel

@Composable
fun HomeScreen(navController: NavController, passViewModel: PassViewModel = viewModel()) {
    var pass by remember { mutableStateOf("") }
    val result: String by passViewModel.response.observeAsState("")
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "調べたいパスワードを\n入力してください",
                style = typography.h5
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                value = pass,
                onValueChange = { pass = it },
                singleLine = true,
                label = { Text("パスワード") }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { passViewModel.getLeakPassList(pass) }) {
                Text(
                    text = "調べる",
                    style = typography.h5
                )
            }
            Text(text = result)
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = "※パスワードは暗号化されて\nから送信されます",
                style = typography.h6,
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}
