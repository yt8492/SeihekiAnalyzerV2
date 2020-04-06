package com.yt8492.seihekianalyzerv2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yt8492.seihekianalyzerv2.R
import com.yt8492.seihekianalyzerv2.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        )
        binding.analyzeButton.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_login)
        }
        return binding.root
    }
}
