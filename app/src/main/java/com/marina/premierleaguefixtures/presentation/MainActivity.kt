package com.marina.premierleaguefixtures.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.marina.premierleaguefixtures.R
import com.marina.premierleaguefixtures.databinding.ActivityMainBinding
import com.marina.premierleaguefixtures.presentation.detail.DetailsFragment
import com.marina.premierleaguefixtures.domain.entity.Match
import com.marina.premierleaguefixtures.presentation.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(ListFragment.newInstance())
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
                    setFragment(ListFragment.newInstance())
                    true
                }
                R.id.second_item -> {
                    setTitleActionBar(R.string.details_fragment)
                    setFragment(DetailsFragment.newInstance(1))
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