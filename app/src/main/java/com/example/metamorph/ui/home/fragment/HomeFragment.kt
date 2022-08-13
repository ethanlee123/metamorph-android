package com.example.metamorph.ui.home.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    // Number of web orders to query by default
    private val DEFAULT_PAGE_SIZE = "10"

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val repository = HomeRepository()
    private lateinit var homeViewModel: HomeViewModel
    private val linearLayoutManager = LinearLayoutManager(context)

    lateinit var adapter: JobsAdapter

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

        setupRecyclerViewJobs()
        setupRecyclerViewAdapter(listOf())
        setupRecyclerViewScrollListener()

        homeViewModel.webOrderResponse.observe(viewLifecycleOwner) { response ->
            adapter.updateList(response)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViewJobs() {
        val rvJobs = binding.rvJobs
        rvJobs.layoutManager = linearLayoutManager
    }

    private fun setupRecyclerViewAdapter(webOrderData: List<WebOrderResponse>) {
        val rvJobs = binding.rvJobs
        adapter = JobsAdapter(webOrderData, this)
        rvJobs.adapter = adapter
    }

    /**
     * Currently the way it gets the quantity of new web orders is by adding 10 to the current
     * amount of web orders. This makes is slower. We want to retrieve only the new web orders.
     */
    private fun setupRecyclerViewScrollListener() {
        binding.rvJobs.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!adapter.isLoading()) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition()
                        == homeViewModel.webOrderResponse.value?.size?.minus(1)
                            ) {

                        binding.progressbar.visibility = View.VISIBLE
                        // load more items after 2 seconds, and remove the loading footer
                        Handler(Looper.getMainLooper()).postDelayed({
                            val webOrderParams = WebOrderParams(
                                pageSize = homeViewModel.webOrderResponse.value?.size?.plus(10).toString()
                            )
                            homeViewModel.getWebOrders(webOrderParams)

                            binding.progressbar.visibility = View.INVISIBLE
                        }, 3000)
                    }
                }
            }
        })
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