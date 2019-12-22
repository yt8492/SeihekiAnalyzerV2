package com.yt8492.seihekianalyzerv2.server.domain.service

import com.yt8492.serihekianalyzerv2.common.domain.model.AnalyzeResult
import com.yt8492.serihekianalyzerv2.common.domain.model.Url

interface AnalyzeService {
    suspend fun analyze(urls: List<Url>): AnalyzeResult
}