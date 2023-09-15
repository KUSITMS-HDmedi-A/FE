package com.kusitms.hdmedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.core.common.SharedPreferenceManager
import com.google.firebase.messaging.FirebaseMessaging
import com.kusitms.hdmedi.core.navigation.NavigationGraphFlow
import com.kusitms.hdmedi.core.navigation.Navigator
import com.kusitms.hdmedi.core.navigation.ToNavGraph
import com.kusitms.hdmedi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToNavGraph {

    lateinit var binding: ActivityMainBinding

    private val navigator = Navigator()

    @Inject
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setContentView(binding.root)
        initNavigation()
        setFCMToken()
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

    private fun setFCMToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener {task ->
            if (!task.isSuccessful) {
                Log.w(javaClass.name, "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d(javaClass.name, "FCM token = $token")

            if (this::sharedPreferenceManager.isInitialized) {
                Log.d(javaClass.name, "this::sharedPreferenceManager.isInitialized")
                sharedPreferenceManager.setFCMToken(token)
            }
        }

    }
}