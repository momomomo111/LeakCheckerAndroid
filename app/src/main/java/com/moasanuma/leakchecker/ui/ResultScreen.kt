package com.moasanuma.leakchecker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moasanuma.leakchecker.R
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
    val result: Int by passViewModel.leakNum.observeAsState(-2)
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (result) {
                -2 -> Text(
                    text = stringResource(R.string.searching_now),
                    style = typography.h2
                )
                -1 -> Text(
                    text = stringResource(R.string.communication_error_messages),
                    style = typography.h5
                )
                0 -> Text(
                    text = stringResource(R.string.leak_not_found),
                    style = typography.h5
                )
                else -> {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = result.toString(),
                            style = typography.h2
                        )
                        Text(
                            text = stringResource(R.string.leak_num),
                            style = typography.h4
                        )
                    }
                    Text(
                        text = stringResource(R.string.detecting_threats),
                        style = typography.h5
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewResultScreen() {
    ResultScreen(rememberNavController(), "")
}
