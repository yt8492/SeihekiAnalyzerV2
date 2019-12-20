package com.yt8492.serihekianalyzerv2.common.domain.model

data class AnalyzeResult(
    val totalCount: Int,
    val tagCounts: Set<TagCount>
)