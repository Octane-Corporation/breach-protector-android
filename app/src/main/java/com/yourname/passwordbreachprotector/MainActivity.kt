package com.octane.pbd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.octane.pbd.ui.CheckScreen
import com.octane.pbd.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val vm = hiltViewModel<com.octane.pbd.viewmodel.CheckViewModel>()
                CheckScreen(viewModel = vm)
            }
        }
    }
}
