package com.yt8492.seihekianalyzerv2.server.domain.service

import com.yt8492.serihekianalyzerv2.common.domain.model.AnalyzeResult
import java.net.URL

interface AnalyzeService {
    suspend fun analyze(urls: List<URL>): AnalyzeResult
}