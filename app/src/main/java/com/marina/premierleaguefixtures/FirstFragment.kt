package com.marina.premierleaguefixtures

import androidx.fragment.app.Fragment
import com.marina.premierleaguefixtures.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding == null")

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(): FirstFragment {
            return FirstFragment()
        }
    }
}