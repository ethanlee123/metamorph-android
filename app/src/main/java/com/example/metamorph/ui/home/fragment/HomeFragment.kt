package com.example.metamorph.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.metamorph.databinding.FragmentHomeBinding
import com.example.metamorph.ui.home.adapter.JobsAdapter
import com.example.metamorph.model.WebOrderParams
import com.example.metamorph.model.WebOrderResponse
import com.example.metamorph.ui.home.repository.HomeRepository
import com.example.metamorph.ui.home.viewmodel.HomeViewModel
import com.example.metamorph.ui.home.viewmodel.HomeViewModelFactory
import com.example.metamorph.ui.orderdetails.OrderDetailsBottomSheetFragment

class HomeFragment : Fragment(), JobsAdapter.IJobRowItemOnClick {
    // Number of web orders to query
    private val DEFAULT_PAGE_SIZE = "10"
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val repository = HomeRepository()
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // make call to VM to retrieve jobs
        val webOrderParams = WebOrderParams(pageSize = DEFAULT_PAGE_SIZE)

        val homeViewModelFactory = HomeViewModelFactory(repository)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]

        homeViewModel.getWebOrders(webOrderParams)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvJobs = binding.rvJobs
        setupRecyclerViewJobs(rvJobs)
        homeViewModel.webOrderResponse.observe(viewLifecycleOwner) { response ->
            setupRecyclerViewAdapter(response)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViewJobs(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
    private fun setupRecyclerViewAdapter(webOrderData: List<WebOrderResponse>) {
        val rvJobs = binding.rvJobs
        rvJobs.adapter = JobsAdapter(webOrderData, this)
    }

    override fun setOnClickListener(orderNo: String) {
        val bottomSheet = OrderDetailsBottomSheetFragment().apply {
            val bundle = Bundle()
            bundle.putString(OrderDetailsBottomSheetFragment.ARG_ORDER_NO, orderNo)
            arguments = bundle
        }

        bottomSheet.show(
            requireActivity().supportFragmentManager,
            OrderDetailsBottomSheetFragment.TAG
        )
    }
}