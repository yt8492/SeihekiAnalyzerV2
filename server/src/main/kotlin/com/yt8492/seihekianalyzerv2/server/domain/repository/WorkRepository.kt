package com.yt8492.seihekianalyzerv2.server.domain.repository

import com.yt8492.serihekianalyzerv2.common.domain.model.Work
import java.net.URL

interface WorkRepository {
    suspend fun findByUrl(url: URL): Work?
    suspend fun save(work: Work)
}