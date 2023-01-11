package com.marina.premierleaguefixtures.presentation.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.marina.premierleaguefixtures.R
import com.marina.premierleaguefixtures.app.App
import com.marina.premierleaguefixtures.databinding.FragmentDetailsBinding
import com.marina.premierleaguefixtures.domain.util.Resource
import com.marina.premierleaguefixtures.presentation.entity.MatchUI
import com.marina.premierleaguefixtures.presentation.view_model_factory.ViewModelFactory
import javax.inject.Inject

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding == null")

    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private val matchId by lazy {
        val args = requireArguments()
        args.getInt(MATCH_ID)
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
        viewModel.loadMatchInfo(matchId)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.match.observe(viewLifecycleOwner) { data ->
            when (data) {
                is Resource.Success -> {
                    setLoading(false)
                    setInfo(data.data!!)
                }
                is Resource.Loading -> {
                    setLoading(true)
                }
                is Resource.Error -> {
                    setLoading(false)
                }
            }
        }
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        root.isVisible = !isLoading
        progressBar.isVisible = isLoading
    }

    private fun setInfo(matchDetails: MatchUI) {
        with(binding) {
            tvMatchNumber.text = matchDetails.matchNumber.toString()
            tvRoundNumber.text = matchDetails.roundNumber.toString()
            tvDate.text = matchDetails.dateUtc
            tvLocation.text = matchDetails.location
            tvHomeTeam.text = matchDetails.homeTeam
            tvAwayTeam.text = matchDetails.awayTeam
            tvGroup.text = matchDetails.group
            tvHomeTeamScore.text = matchDetails.homeTeamScore.toString()
            tvAwayTeamScore.text = matchDetails.awayTeamScore.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val MATCH_ID = "match_id"

        fun newInstance(id: Int): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(MATCH_ID, id)
                }
            }
        }
    }
}