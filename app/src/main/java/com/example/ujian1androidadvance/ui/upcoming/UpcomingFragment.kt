package com.example.ujian1androidadvance.ui.upcoming

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujian1androidadvance.DetailEventActivity
import com.example.ujian1androidadvance.data.remote.response.ListEventsItem
import com.example.ujian1androidadvance.databinding.FragmentUpcomingBinding

class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    private lateinit var upcomingAdapter: UpcomingAdapter
    private lateinit var upcomingViewModel: UpcomingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upcomingViewModel = ViewModelProvider(this).get(UpcomingViewModel::class.java)

        upcomingAdapter = UpcomingAdapter()
        binding.rv1.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.rv1.adapter = upcomingAdapter

        observeViewModel()
        upcomingViewModel.fetchUpcomingEvent()

        upcomingAdapter.setOnItemClickCallback(object : UpcomingAdapter.OnItemClickCallback {
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
        upcomingViewModel.upcomingEvent.observe(viewLifecycleOwner){
            upcomingAdapter.submitList(it)
        }
        upcomingViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        upcomingViewModel.errorMessage.observe(viewLifecycleOwner) {
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