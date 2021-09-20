package com.moasanuma.leakchecker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moasanuma.leakchecker.viewmodel.PassViewModel

@Composable
fun ResultScreen(
    navController: NavController,
    pass: String?,
    passViewModel: PassViewModel = viewModel()
) {
    if (pass != null) {
        passViewModel.getLeakPassList(pass)
    }
    val result: String by passViewModel.response.observeAsState("")
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = result + "件の脅威が発見されました")
        }
    }
}

@Preview
@Composable
private fun PreviewResultScreen() {
    ResultScreen(rememberNavController(), "")
}
