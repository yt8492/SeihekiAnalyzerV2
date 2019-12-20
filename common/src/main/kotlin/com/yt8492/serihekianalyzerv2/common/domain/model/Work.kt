package com.yt8492.serihekianalyzerv2.common.domain.model

import java.net.URL

data class Work(
    val url: URL,
    val tags: Set<Tag>
)