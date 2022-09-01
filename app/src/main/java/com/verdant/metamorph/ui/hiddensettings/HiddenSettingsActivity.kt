package com.verdant.metamorph.ui.hiddensettings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.verdant.metamorph.R
import com.verdant.metamorph.databinding.ActivityHiddenSettingsBinding

class HiddenSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHiddenSettingsBinding
    lateinit var hiddenSettingsViewModel: HiddenSettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiddenSettingsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        hiddenSettingsViewModel = ViewModelProvider(this)[HiddenSettingsViewModel::class.java]
        setupToolbar()
        setupDeviceTokenTextView()
    }

    private fun setupDeviceTokenTextView() {
        hiddenSettingsViewModel.deviceTokenText.observe(this) {
            binding.tvDeviceToken.text = it
        }
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
        const val TAG = "HiddenSettingsActivity"

        fun getIntent(context: Context): Intent {
            return Intent(context, HiddenSettingsActivity::class.java)
        }
    }
}