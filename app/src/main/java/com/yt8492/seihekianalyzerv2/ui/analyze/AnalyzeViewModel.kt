package com.yt8492.seihekianalyzerv2.ui.analyze

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yt8492.seihekianalyzerv2.common.scraper.DLsiteScraperWithJsoup
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeResult
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeUseCase
import com.yt8492.seihekianalyzerv2.ui.bindingmodel.AnalyzeResultBindingModel
import com.yt8492.seihekianalyzerv2.ui.toBindingModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnalyzeViewModel(
    private val analyzeUseCase: SeihekiAnalyzeUseCase
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _analyzeResult = MutableLiveData<AnalyzeResultBindingModel>()
    val analyzeResult: LiveData<AnalyzeResultBindingModel>
        get() = _analyzeResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun analyze(loginCookies: Map<String, String>): Job = viewModelScope.launch {
        _loading.value = true
        val urls = DLsiteScraperWithJsoup.scrapeAllUserBoughtUrls(loginCookies)
        when (val analyzeResult = analyzeUseCase.execute(urls)) {
            is SeihekiAnalyzeResult.Success -> {
                _loading.value = false
                _analyzeResult.value = analyzeResult.result.toBindingModel()
            }
            is SeihekiAnalyzeResult.Failure -> {
                _loading.value = false
                val message = analyzeResult.cause.message
                _errorMessage.value = message
            }
        }
    }
}