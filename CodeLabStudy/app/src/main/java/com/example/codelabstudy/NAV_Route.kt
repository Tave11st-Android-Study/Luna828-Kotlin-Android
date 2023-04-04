package com.example.codelabstudy

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.codelabstudy.home.Home
import com.example.codelabstudy.home.HomeClone

enum class NAV_Route(val routeName: String, val description: String) {
    MAIN ("MAIN", "메인화면"),
    PAGE2 ("SECONDPAGE", "페이지2")
}

@Composable
fun Screen(startRoute: String){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startRoute,
    ){
        composable(NAV_Route.MAIN.routeName){
            Home(
                navController,
                onHomeClone = {
                    navController.navigate(NAV_Route.PAGE2.routeName)
                }
            )
        }
        composable(NAV_Route.PAGE2.routeName){
            HomeClone(navController)
        }
    }
}