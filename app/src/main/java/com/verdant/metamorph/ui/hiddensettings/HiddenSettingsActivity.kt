package com.verdant.metamorph.ui.hiddensettings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.verdant.metamorph.R
import com.verdant.metamorph.databinding.ActivityHiddenSettingsBinding

class HiddenSettingsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHiddenSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiddenSettingsBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_hidden_settings)

        setupToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupToolbar() {
        // binding.myToolbar doesnt work
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HiddenSettingsActivity::class.java)
        }
    }
}