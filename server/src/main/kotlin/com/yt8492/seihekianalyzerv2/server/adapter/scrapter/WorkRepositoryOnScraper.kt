package com.yt8492.seihekianalyzerv2.server.adapter.scrapter

import com.yt8492.seihekianalyzerv2.server.domain.repository.WorkRepository
import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.domain.model.Work
import com.yt8492.seihekianalyzerv2.common.scraper.DLsiteScraperWithJsoup
import kotlinx.coroutines.delay

class WorkRepositoryOnScraper(
    private val dlsiteScraperWithJsoup: DLsiteScraperWithJsoup
) : WorkRepository {
    override suspend fun findByUrl(url: Url): Work? {
        delay(1000)
        return dlsiteScraperWithJsoup.scrapeWorkByUrl(url)
    }

    override suspend fun findAllByUrls(urls: List<Url>): List<Work> {
        return urls.map {
            delay(1000)
            dlsiteScraperWithJsoup.scrapeWorkByUrl(it)
        }
    }

    override suspend fun save(work: Work) {
        error("this is scraper. don't call save.")
    }
}