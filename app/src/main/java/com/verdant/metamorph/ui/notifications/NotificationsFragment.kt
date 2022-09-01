package com.verdant.metamorph.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.verdant.metamorph.databinding.FragmentNotificationsBinding
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

//        setupRecyclerViewJobs()
//        setupRecyclerViewAdapter(listOf())

//        notificationsViewModel.notificationRows.observe(viewLifecycleOwner) { response ->
//            adapter.updateList(response)
//        }

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

//    private fun setupRecyclerViewAdapter(webOrderData: List<WebOrderResponse>) {
//        val rvJobs = binding.rvJobs
//        adapter = NotificationRowAdapter(webOrderData, this)
//        rvJobs.adapter = adapter
//    }


//    override fun setOnClickListener(orderNo: String) {
//        val bottomSheet = OrderDetailsBottomSheetFragment().apply {
//            val bundle = Bundle()
//            bundle.putString(OrderDetailsBottomSheetFragment.ARG_ORDER_NO, orderNo)
//            arguments = bundle
//        }
//
//        bottomSheet.show(
//            requireActivity().supportFragmentManager,
//            OrderDetailsBottomSheetFragment.TAG
//        )
//    }
}