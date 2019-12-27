package com.yt8492.seihekianalyzerv2.common.scraper

import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.domain.model.Work

interface DLsiteScraper {
    suspend fun scrapeAllUserBoughtUrls(loginCookies: Map<String, String>): List<Url>
    suspend fun scrapeWorkByUrl(Url: Url): Work
    suspend fun scrapeAllTodayWorks(): List<Work>
}