package com.kusitms.hdmedi.core.navigation

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController

    fun navigateToGraph(navGraph: NavigationGraphFlow) = when (navGraph) {
        NavigationGraphFlow.BottomGraphFlow -> navController.navigate(R.id.action_signin_graph_to_bottom_graph)
    }
}