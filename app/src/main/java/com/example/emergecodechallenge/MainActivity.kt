package com.example.emergecodechallenge

import android.os.Bundle
import androidx.activity.viewModels

class MainActivity : androidx.activity.ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.CreationFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.pageState.observe(this) {
            TODO("STOPSHIP")
        }
    }
}
