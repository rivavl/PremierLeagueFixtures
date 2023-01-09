package com.marina.premierleaguefixtures.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marina.premierleaguefixtures.R
import com.marina.premierleaguefixtures.data.Resource
import com.marina.premierleaguefixtures.databinding.FragmentListBinding
import com.marina.premierleaguefixtures.presentation.adapter.MatchAdapter
import com.marina.premierleaguefixtures.presentation.detail.DetailsFragment

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding ?: throw RuntimeException("FragmentListBinding == null")

    private lateinit var recyclerView: RecyclerView
    private lateinit var matchesListAdapter: MatchAdapter
    private lateinit var viewModel: ListFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListFragmentViewModel::class.java]
        viewModel.app = requireActivity().application
        setupRecyclerView()
        setupClickListener()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.matchesList.observe(viewLifecycleOwner) { data ->
            when (data) {
                is Resource.Success -> {
                    setLoading(false)
                    matchesListAdapter.submitList(data.data)
                }
                is Resource.Loading -> {
                    setLoading(true)
                }
                is Resource.Error -> {}
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            setLoading(it)
        }
    }

    private fun setupRecyclerView() {
        recyclerView = binding.rvMatches
        matchesListAdapter = MatchAdapter()
        recyclerView.apply {
            adapter = matchesListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        matchesListAdapter.onMatchItemClick = {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, DetailsFragment.newInstance(it))
                .commit()
        }
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        rvMatches.isVisible = !isLoading
        progressBar.isVisible = isLoading
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}