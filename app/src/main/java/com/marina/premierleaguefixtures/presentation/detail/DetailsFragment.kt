package com.marina.premierleaguefixtures.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.marina.premierleaguefixtures.R
import com.marina.premierleaguefixtures.app.App
import com.marina.premierleaguefixtures.data.local.AppDatabase
import com.marina.premierleaguefixtures.data.remote.RetrofitInstance
import com.marina.premierleaguefixtures.data.repository.MatchRepositoryImpl
import com.marina.premierleaguefixtures.databinding.FragmentDetailsBinding
import com.marina.premierleaguefixtures.domain.repository.MatchRepository
import com.marina.premierleaguefixtures.domain.use_case.GetMatchByIdUseCase
import com.marina.premierleaguefixtures.domain.util.Resource
import com.marina.premierleaguefixtures.presentation.entity.MatchUI

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding == null")

    private val matchId by lazy {
        val args = requireArguments()
        args.getInt(MATCH_ID)
    }

    // ---------------------------------------
    val app by lazy { (requireActivity().application as App) }
    val repo: MatchRepository by lazy {
        MatchRepositoryImpl(
            api = RetrofitInstance.matchesApi,
            dao = AppDatabase.getInstance(app).matchDao
        )
    }
    val useCase by lazy { GetMatchByIdUseCase(repo) }
    //-----------------------------------------

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(useCase)
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