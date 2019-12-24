package com.yt8492.seihekianalyzerv2.ui.analyze

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeUseCase
import javax.inject.Inject

class AnalyzeViewModelProvider @Inject constructor(
    private val seihekiAnalyzeUseCase: SeihekiAnalyzeUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == AnalyzeViewModel::class.java) {
            "Unknown ViewModel Class"
        }
        return AnalyzeViewModel(
            seihekiAnalyzeUseCase
        ) as T
    }
}