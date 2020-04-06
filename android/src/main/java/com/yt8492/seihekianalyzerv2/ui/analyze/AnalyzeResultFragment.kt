package com.yt8492.seihekianalyzerv2.ui.analyze

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yt8492.seihekianalyzerv2.databinding.FragmentAnalyzeResultBinding
import com.yt8492.seihekianalyzerv2.ui.bindingmodel.AnalyzeResultBindingModel
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
            val dividerItemDecoration = DividerItemDecoration(
                inflater.context,
                linearLayoutManager.orientation
            )
            addItemDecoration(dividerItemDecoration)
            adapter = recyclerAdapter
        }
        binding.tweetButton.setOnClickListener {
            viewModel.analyzeResult.value?.let {
                tweetSeiheki(it)
            }
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

    private fun tweetSeiheki(analyzeResult: AnalyzeResultBindingModel) {
        val intent = Intent(Intent.ACTION_VIEW)
        val content = buildString {
            appendln("Seiheki Analyze Result")
            appendln("購入数: ${analyzeResult.totalCount}")
            analyzeResult.tagCounts.forEach {
                appendln("${it.tag.value}: ${String.format("%.2f", it.percentage)}")
            }
        }
        val encodedContent = Uri.encode(content)
        intent.data = Uri.parse("twitter://post?message=$encodedContent")
        startActivity(intent)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
