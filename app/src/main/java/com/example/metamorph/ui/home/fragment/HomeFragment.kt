package com.example.metamorph.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.metamorph.databinding.FragmentHomeBinding
import com.example.metamorph.ui.home.adapter.JobsAdapter
import com.example.metamorph.ui.home.model.WebOrderParams
import com.example.metamorph.ui.home.model.WebOrderResponse
import com.example.metamorph.ui.home.repository.HomeRepository
import com.example.metamorph.ui.home.viewmodel.HomeViewModel
import com.example.metamorph.ui.home.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val repository = HomeRepository()
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // make call to VM to retrieve jobs
        val webOrderParams = WebOrderParams()

        val homeViewModelFactory = HomeViewModelFactory(repository)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]

        homeViewModel.getJobDetails(webOrderParams)
        homeViewModel.webOrderResponse.observe(this) { response ->
//            if (response.isSuccessful){
            Log.i("resp: ", response.toString())
//            } else {
//                Log.i("resp: ", response.toString())
//            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvJobs = binding.rvJobs
        setupRecyclerViewJobs(rvJobs)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViewJobs(recyclerView: RecyclerView) {
        val mockData = arrayOf(
            WebOrderResponse(
                date = "testing",
                time = "testing",
                paid = "testing",
                earn = "testing",
                status = "testing",
                from = "testing",
                order = "testing",
                trn = "testing"),
            WebOrderResponse(
                date = "testing",
                time = "testing",
                paid = "testing",
                earn = "testing",
                status = "testing",
                from = "testing",
                order = "testing",
                trn = "testing")
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = JobsAdapter(mockData)
    }
}