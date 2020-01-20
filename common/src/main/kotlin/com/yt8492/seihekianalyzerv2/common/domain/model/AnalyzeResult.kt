package com.yt8492.seihekianalyzerv2.common.domain.model

data class AnalyzeResult(
    val totalCount: Int,
    val successCount: Int,
    val tagCounts: List<TagCount>
)