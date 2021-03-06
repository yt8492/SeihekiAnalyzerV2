package com.yt8492.seihekianalyzerv2.domain.service

import com.yt8492.seihekianalyzerv2.common.domain.model.AnalyzeResult
import com.yt8492.seihekianalyzerv2.common.domain.model.WorkNameAndUrl

interface AnalyzeService {
    suspend fun analyze(workNameAndUrls: List<WorkNameAndUrl>): AnalyzeResult
}
