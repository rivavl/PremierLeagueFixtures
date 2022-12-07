package com.marina.premierleaguefixtures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.marina.premierleaguefixtures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
                    setTitleActionBar(R.string.second_fragment)
                    setFragment(SecondFragment.newInstance())
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