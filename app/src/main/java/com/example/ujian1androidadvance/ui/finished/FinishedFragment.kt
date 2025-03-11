package com.example.ujian1androidadvance.ui.finished

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujian1androidadvance.DetailEventActivity
import com.example.ujian1androidadvance.data.remote.response.ListEventsItem
import com.example.ujian1androidadvance.databinding.FragmentFinishedBinding

class FinishedFragment : Fragment() {

    private var _binding: FragmentFinishedBinding? = null
    private val binding get() = _binding!!

    private lateinit var finishedAdapter: FinishedAdapter
    private lateinit var finishedViewModel: FinishedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finishedViewModel = ViewModelProvider(this).get(FinishedViewModel::class.java)

        finishedAdapter = FinishedAdapter()
        binding.rv2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.rv2.adapter = finishedAdapter

        observeViewModel()
        finishedViewModel.fetchFinishedEvent()

        finishedAdapter.setOnItemClickCallback(object : FinishedAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListEventsItem) {
                onItemClick(data)
            }
        })

    }

    private fun onItemClick(listEventsItem: ListEventsItem) {
        val intent = Intent(requireContext(), DetailEventActivity::class.java)
        intent.putExtra(DetailEventActivity.EXTRA_EVENT, listEventsItem)
        startActivity(intent)
    }

    private fun observeViewModel() {
        finishedViewModel.upcomingEvent.observe(viewLifecycleOwner){
            finishedAdapter.submitList(it)
        }
        finishedViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        finishedViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}