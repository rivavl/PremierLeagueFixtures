package com.marina.premierleaguefixtures

import androidx.fragment.app.Fragment
import com.marina.premierleaguefixtures.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding
        get() = _binding ?: throw RuntimeException("FragmentSecondBinding == null")

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(): SecondFragment {
            return SecondFragment()
        }
    }
}