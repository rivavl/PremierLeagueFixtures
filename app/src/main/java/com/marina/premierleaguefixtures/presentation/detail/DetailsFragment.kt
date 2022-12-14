package com.marina.premierleaguefixtures.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marina.premierleaguefixtures.R
import com.marina.premierleaguefixtures.databinding.FragmentDetailsBinding
import com.marina.premierleaguefixtures.model.Match

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding == null")

    private val matchDetails by lazy {
        val args = requireArguments()
        args.getParcelable<Match>(MATCH_DETAILS)
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
        parseArgs()
    }

    private fun parseArgs() {
        if (matchDetails == null) return

        with(binding) {
            tvMatchNumber.text = matchDetails!!.matchNumber.toString()
            tvRoundNumber.text = matchDetails!!.roundNumber.toString()
            tvDate.text = matchDetails!!.dateUtc.toString()
            tvLocation.text = matchDetails!!.location
            tvHomeTeam.text = matchDetails!!.homeTeam
            tvAwayTeam.text = matchDetails!!.awayTeam
            tvGroup.text = matchDetails!!.group
            tvHomeTeamScore.text = matchDetails!!.homeTeamScore.toString()
            tvAwayTeamScore.text = matchDetails!!.awayTeamScore.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val MATCH_DETAILS = "match_details"

        fun newInstance(match: Match): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MATCH_DETAILS, match)
                }
            }
        }
    }
}