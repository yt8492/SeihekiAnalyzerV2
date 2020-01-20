package com.yt8492.seihekianalyzerv2.common.usecase.analyze

import com.yt8492.seihekianalyzerv2.common.domain.model.WorkNameAndUrl

interface SeihekiAnalyzeUseCase {
    suspend fun execute(workNameAndUrls: List<WorkNameAndUrl>): SeihekiAnalyzeResult
}