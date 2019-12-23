package com.yt8492.seihekianalyzerv2.domain.service

import com.yt8492.seihekianalyzerv2.common.domain.model.AnalyzeResult
import com.yt8492.seihekianalyzerv2.common.domain.model.Url

interface AnalyzeService {
    suspend fun analyze(urls: List<Url>): AnalyzeResult
}