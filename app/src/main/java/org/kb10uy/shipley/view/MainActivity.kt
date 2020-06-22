package org.kb10uy.shipley.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import org.kb10uy.shipley.R
import org.kb10uy.shipley.databinding.ActivityMainBinding
import org.kb10uy.shipley.databinding.NavHeaderMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val drawerLayout = binding.container
        val navView = binding.navView
        val toolbar = binding.toolbar
        val headerBinding = NavHeaderMainBinding.bind(navView.getHeaderView(0))

        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        toolbar.inflateMenu(R.menu.main_option)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_settings -> {
                    navController.navigate(R.id.action_global_to_settings)
                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val menu = toolbar.menu
            when (destination.id) {
                R.id.nav_settings -> {
                    menu.findItem(R.id.action_settings).isVisible = false
                }
                R.id.nav_account_list -> {
                    menu.findItem(R.id.action_settings).isVisible = false
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                else -> {
                    menu.findItem(R.id.action_settings).isVisible = true
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
            }
        }

        headerBinding.imageViewAccountAvatar.setOnClickListener {
            navController.navigate(R.id.action_home_to_account_list)
            drawerLayout.closeDrawers()
        }
    }
}
