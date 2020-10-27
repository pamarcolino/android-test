package com.pamarcolino.sofie.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.pamarcolino.sofie.R
import com.pamarcolino.sofie.model.Task
import com.pamarcolino.sofie.view.fragments.TasksFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var toolbar: Toolbar? = null
    private var tasks: ArrayList<Task>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        navController = findNavController(R.id.nav_host_fragment)

        setupNavigation()
    }

    private fun setupNavigation() {
        appBarConfiguration = AppBarConfiguration(navController.graph)

        navController.addOnDestinationChangedListener { _, destination, _ ->
           toolbar?.title =
                when (destination.id) {
                    R.id.newTaskFragment -> getString(R.string.lbl_new_task)
                    else -> getString(R.string.app_name)
                }
        }

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}