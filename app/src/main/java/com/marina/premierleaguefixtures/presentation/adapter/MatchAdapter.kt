package com.marina.premierleaguefixtures.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marina.premierleaguefixtures.R
import com.marina.premierleaguefixtures.databinding.MatchItemBinding
import com.marina.premierleaguefixtures.model.Match


class MatchAdapter :
    ListAdapter<Match, MatchAdapter.MatchViewHolder>(MatchDiffUtilCallback()) {

    var onMatchItemClick: ((Match) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = MatchItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = getItem(position)
        val context = holder.binding.root.context
        with(holder.binding) {
            tvHomeTeamName.text = match.homeTeam
            tvAwayTeamName.text = match.awayTeam
            tvHomeTeamScore.text = match.homeTeamScore.toString()
            tvAwayTeamScore.text = match.awayTeamScore.toString()

            setScoreColors(this, match, context)

            itemView.setOnClickListener {
                onMatchItemClick?.invoke(match)
            }
        }
    }

    private fun setScoreColors(binding: MatchItemBinding, match: Match, context: Context) {
        val colors = getScoreColors(match.homeTeamScore, match.awayTeamScore, context)
        with(binding) {
            tvHomeTeamScore.setTextColor(colors.first)
            tvAwayTeamScore.setTextColor(colors.second)
        }

    }

    private fun getScoreColors(
        homeScore: Int,
        awayScore: Int,
        context: Context
    ): Pair<Int, Int> {
        return if (homeScore > awayScore) {
            Pair(context.getColor(R.color.green), context.getColor(R.color.red))
        } else if (awayScore > homeScore) {
            Pair(context.getColor(R.color.red), context.getColor(R.color.green))
        } else {
            Pair(context.getColor(R.color.gray), context.getColor(R.color.gray))
        }
    }


    class MatchViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root)

    class MatchDiffUtilCallback : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem.matchNumber == newItem.matchNumber
        }

        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem == newItem
        }

    }
}