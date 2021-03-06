package com.yt8492.seihekianalyzerv2.server.domain.repository

import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.domain.model.Work

interface WorkRepository {
    suspend fun findByUrl(url: Url): Work.OnSale?
    suspend fun findAllByUrls(urls: List<Url>): List<Work.OnSale>
    suspend fun save(work: Work)
}
