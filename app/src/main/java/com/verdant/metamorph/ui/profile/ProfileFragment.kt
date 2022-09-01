package com.verdant.metamorph.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.verdant.metamorph.databinding.FragmentProfileBinding
import com.verdant.metamorph.ui.hiddensettings.HiddenSettingsActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var hiddenSettingsAccessClickCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        textView.text = "Coming soon!"

        setupToolbar()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupToolbar() {
        val toolbar = binding.myToolbar
        toolbar.setOnClickListener {
            hiddenSettingsAccessClickCount += 1

            if (hiddenSettingsAccessClickCount == HIDDEN_SETTINGS_ACCESS_MIN_CLICKS) {
                val intent = HiddenSettingsActivity.getIntent(requireContext())
                startActivity(intent)
                hiddenSettingsAccessClickCount = 0
            }
        }
    }

    companion object {
        const val TAG = "PROFILE_FRAGMENT"
        const val HIDDEN_SETTINGS_ACCESS_MIN_CLICKS = 5
    }
}