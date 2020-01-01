package com.yt8492.seihekianalyzerv2.server.adapter.scrapter

import com.yt8492.seihekianalyzerv2.server.domain.repository.WorkRepository
import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.domain.model.Work
import com.yt8492.seihekianalyzerv2.common.scraper.DLsiteScraper
import kotlinx.coroutines.delay

class WorkRepositoryOnScraper(
    private val dlsiteScraper: DLsiteScraper
) : WorkRepository {
    override suspend fun findByUrl(url: Url): Work? {
        return dlsiteScraper.scrapeWorkByUrl(url)
    }

    override suspend fun findAllByUrls(urls: List<Url>): List<Work> {
        return urls.map {
            dlsiteScraper.scrapeWorkByUrl(it)
        }
    }

    override suspend fun save(work: Work) {
        error("this is scraper. don't call save.")
    }
}