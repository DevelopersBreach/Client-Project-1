package com.developersbreach.clientproject.view.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.databinding.ActivityMainBinding
import com.developersbreach.clientproject.utils.hideStatusBar
import com.developersbreach.clientproject.utils.showStatusBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        this.findNavController(R.id.nav_host_fragment).let { navController ->
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.introFragment -> {
                        hideStatusBar(this@MainActivity)
                        setStatusBarColor(
                            R.color.shiva_pink_dark_primary,
                            R.color.shiva_pink_dark_primary
                        )
                    }

                    R.id.loginFragment, R.id.achievementsFragment -> {
                        showStatusBar(this@MainActivity)
                        setStatusBarColor(
                            R.color.shiva_pink_dark_primary,
                            R.color.shiva_pink_dark_primary
                        )
                    }

                    R.id.submissionsFragment -> {
                        showStatusBar(this@MainActivity)
                        setStatusBarColor(
                            R.color.shiva_pink_dark_primary,
                            R.color.shiva_pink_nav_bar
                        )
                    }

                    R.id.editorFragment, R.id.aboutFragment, R.id.billNumberFragment,
                    R.id.contactFragment, R.id.servicesFragment, R.id.detailFragment -> {
                        showStatusBar(this@MainActivity)
                        setStatusBarColor(
                            R.color.shiva_ash_dark_primary,
                            R.color.shiva_ash_dark_primary
                        )
                    }
                    else -> showStatusBar(this@MainActivity)
                }
            }
        }
    }

    private fun setStatusBarColor(statusBarColor: Int, navigationBarColor: Int) {
        window.statusBarColor = ContextCompat.getColor(applicationContext, statusBarColor)
        window.navigationBarColor = ContextCompat.getColor(applicationContext, navigationBarColor)
    }
}