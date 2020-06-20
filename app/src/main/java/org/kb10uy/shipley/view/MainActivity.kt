package org.kb10uy.shipley.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import org.kb10uy.shipley.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.container)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        toolbar.inflateMenu(R.menu.main_option)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_settings -> {
                    navController.navigate(R.id.action_home_to_settings)
                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener {_, destination, _ ->
            val menu = toolbar.menu
            when (destination.id) {
                R.id.nav_settings -> {
                    menu.findItem(R.id.action_settings).isVisible = false
                }
                else -> {
                    menu.findItem(R.id.action_settings).isVisible = true
                }
            }
        }
    }
}
