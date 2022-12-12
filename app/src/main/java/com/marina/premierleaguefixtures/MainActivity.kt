package com.marina.premierleaguefixtures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.marina.premierleaguefixtures.databinding.ActivityMainBinding
import com.marina.premierleaguefixtures.model.Match

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val match by lazy {
        Match(
            matchNumber = 1,
            roundNumber = 1,
            dateUtc = "2021-08-13 19:00:00",
            location = "Brentford Community Stadium",
            homeTeam = "Brentford",
            awayTeam = "Arsenal",
            group = null,
            homeTeamScore = 2,
            awayTeamScore = 0
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(FirstFragment.newInstance())
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val bottomNavigationView = binding.bNav
        bottomNavigationView.menu.findItem(R.id.first_item).isChecked = true
        supportActionBar?.title = getString(R.string.first_fragment).uppercase()
        binding.bNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.first_item -> {
                    setTitleActionBar(R.string.first_fragment)
                    setFragment(FirstFragment.newInstance())
                    true
                }
                R.id.second_item -> {
                    setTitleActionBar(R.string.details_fragment)
                    setFragment(DetailsFragment.newInstance(match))
                    true
                }
                else -> false
            }
        }
    }

    private fun setTitleActionBar(id: Int) {
        supportActionBar?.title = getString(id).uppercase()
    }

    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}