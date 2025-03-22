package com.example.ujian1androidadvance.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujian1androidadvance.MainViewModel
import com.example.ujian1androidadvance.MainViewModelFactory
import com.example.ujian1androidadvance.data.adapter.AdapterEvent
import com.example.ujian1androidadvance.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var verticalAdapter: AdapterEvent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory: MainViewModelFactory = MainViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        verticalAdapter = AdapterEvent { events ->
            if (events.isFavorite == true) {
                viewModel.deleteEvents(events)
            } else {
                viewModel.saveEvents(events)
            }
        }

        verticalAdapter.setLoadingState(true)

        viewModel.getFavoriteEvents().observe(viewLifecycleOwner) { favoriteEvents ->
            binding.progressBar.visibility = View.GONE
            verticalAdapter.setLoadingState(false)
            verticalAdapter.submitList(favoriteEvents)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = verticalAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}