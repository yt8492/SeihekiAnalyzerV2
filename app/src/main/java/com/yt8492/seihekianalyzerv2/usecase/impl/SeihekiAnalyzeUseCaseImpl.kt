package com.yt8492.seihekianalyzerv2.usecase.impl

import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeResult
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeUseCase
import com.yt8492.seihekianalyzerv2.domain.service.AnalyzeService

class SeihekiAnalyzeUseCaseImpl constructor(
    private val analyzeService: AnalyzeService
) : SeihekiAnalyzeUseCase {
    override suspend fun execute(urls: List<Url>): SeihekiAnalyzeResult {
        return try {
            val analyzeResult = analyzeService.analyze(urls)
            SeihekiAnalyzeResult.Success(analyzeResult)
        } catch (e: Exception) {
            SeihekiAnalyzeResult.Failure(e)
        }
    }
}