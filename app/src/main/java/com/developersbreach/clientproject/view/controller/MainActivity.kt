package com.developersbreach.clientproject.view.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        val activity = this
        activity.findNavController(R.id.nav_host_fragment).apply {
            this.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.introFragment) {
                    hideStatusBar(activity)
                } else {
                    showStatusBar(activity)
                }
            }
        }
    }
}