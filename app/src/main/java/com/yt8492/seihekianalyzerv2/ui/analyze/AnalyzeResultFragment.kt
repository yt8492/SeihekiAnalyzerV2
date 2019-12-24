package com.yt8492.seihekianalyzerv2.ui.analyze

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yt8492.seihekianalyzerv2.databinding.FragmentAnalyzeResultBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AnalyzeResultFragment : Fragment() {

    @Inject
    lateinit var analyzeViewModelProvider: AnalyzeViewModelProvider

    private val viewModel by activityViewModels<AnalyzeViewModel> {
        analyzeViewModelProvider
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAnalyzeResultBinding.inflate(
            inflater,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val recyclerAdapter = TagCountRecyclerAdapter()
        binding.tagCountsRecyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(inflater.context)
            val dividerItemDecoration = DividerItemDecoration(inflater.context, linearLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            adapter = recyclerAdapter
        }
        viewModel.analyzeResult.observe(viewLifecycleOwner, Observer {
            val tagCounts = it.tagCounts
            recyclerAdapter.submitList(tagCounts)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}