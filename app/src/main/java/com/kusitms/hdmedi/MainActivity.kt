package com.kusitms.hdmedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.kusitms.hdmedi.core.navigation.NavigationGraphFlow
import com.kusitms.hdmedi.core.navigation.Navigator
import com.kusitms.hdmedi.core.navigation.ToNavGraph
import com.kusitms.hdmedi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToNavGraph {

    lateinit var binding: ActivityMainBinding

    private val navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setContentView(binding.root)
        initNavigation()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navigator.navController = navController

        binding.navBar.setupWithNavController(navController)

        // AppBar 설정
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.medicineFragment,
                R.id.searchFragment,
                R.id.selfCheckFragment,
                R.id.mypageFragment
            )
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navBar.isVisible =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
        }
    }

    override fun navigateToGraph(flow: NavigationGraphFlow) {
        navigator.navigateToGraph(flow)
    }
}