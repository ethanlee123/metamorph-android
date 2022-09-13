package com.verdant.metamorph.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.verdant.metamorph.databinding.FragmentNotificationsBinding
import com.verdant.metamorph.model.NotificationRowResponse
import com.verdant.metamorph.ui.notifications.viewmodel.NotificationsViewModelFactory
import com.verdant.metamorph.ui.notifications.adapter.NotificationRowAdapter
import com.verdant.metamorph.ui.notifications.repository.NotificationsRepository

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private val notificationsRepository = NotificationsRepository()
    private lateinit var notificationsViewModel: NotificationsViewModel
    lateinit var adapter: NotificationRowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationViewModelFactory = NotificationsViewModelFactory(notificationsRepository)
        notificationsViewModel =
            ViewModelProvider(this, notificationViewModelFactory)[NotificationsViewModel::class.java]

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Must setup recycler view on main thread or you'll get no adapter attached error
        setupRecyclerViewJobs()
        setupRecyclerViewAdapter(listOf())

        notificationsViewModel.fetchPushNotifications()
        setupObservers()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViewJobs() {
        val rvJobs = binding.rvJobs
        rvJobs.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        notificationsViewModel.notificationRows.observe(viewLifecycleOwner) { notificationDataSet ->
            if (notificationDataSet.isEmpty()) {
                binding.llNoNotifications.visibility = View.VISIBLE
            } else {
                binding.llNoNotifications.visibility = View.GONE
                adapter.updateList(notificationDataSet)
            }

            // Hide progress bar after fetching data from DB
            binding.progressbar.visibility = View.GONE
        }
    }

    private fun setupRecyclerViewAdapter(pushNotifications: List<NotificationRowResponse>) {
        val rvJobs = binding.rvJobs
        adapter = NotificationRowAdapter(pushNotifications)
        rvJobs.adapter = adapter
    }
}