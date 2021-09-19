package com.moasanuma.leakchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.moasanuma.leakchecker.ui.HomeScreen
import com.moasanuma.leakchecker.ui.theme.LeakCheckerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeakCheckerTheme {
                HomeScreen()
            }
        }
    }
}
