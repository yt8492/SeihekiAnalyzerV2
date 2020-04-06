package com.yt8492.seihekianalyzerv2.common.usecase.analyze

import com.yt8492.seihekianalyzerv2.common.domain.model.AnalyzeResult

sealed class SeihekiAnalyzeResult {
    class Success(val result: AnalyzeResult) : SeihekiAnalyzeResult()
    class Failure(val cause: Throwable) : SeihekiAnalyzeResult()
}
