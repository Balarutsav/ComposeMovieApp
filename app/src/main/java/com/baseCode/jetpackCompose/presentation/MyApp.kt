package com.baseCode.jetpackCompose.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.baseCode.jetpackCompose.presentation.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine

@Composable
fun MyApp() {

    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()
    Scaffold {

        DestinationsNavHost(
            navGraph = NavGraphs.root,
            startRoute = LoginScreenDestination,
            navController = navController,
            modifier = Modifier.padding(it)
        )


    }


}