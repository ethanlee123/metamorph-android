package com.example.metamorph.ui.home

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
import com.example.metamorph.ui.home.model.JobDetailModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

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
            JobDetailModel(
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