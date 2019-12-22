package com.yt8492.seihekianalyzerv2.server.usecase.analyze

import com.yt8492.serihekianalyzerv2.common.domain.model.Url


interface SeihekiAnalyzeUseCase {
    suspend fun execute(urls: List<Url>): SeihekiAnalyzeResult
}