package com.yt8492.seihekianalyzerv2.usecase.impl

import android.util.Log
import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeResult
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeUseCase
import com.yt8492.seihekianalyzerv2.domain.service.AnalyzeService
import javax.inject.Inject

class SeihekiAnalyzeUseCaseImpl @Inject constructor(
    private val analyzeService: AnalyzeService
) : SeihekiAnalyzeUseCase {
    override suspend fun execute(urls: List<Url>): SeihekiAnalyzeResult {
        return try {
            val analyzeResult = analyzeService.analyze(urls)
            Log.d("hogehoge", "analyze success")
            SeihekiAnalyzeResult.Success(analyzeResult)
        } catch (e: Exception) {
            Log.e("hogehoge", "analyze failed", e)
            SeihekiAnalyzeResult.Failure(e)
        }
    }
}