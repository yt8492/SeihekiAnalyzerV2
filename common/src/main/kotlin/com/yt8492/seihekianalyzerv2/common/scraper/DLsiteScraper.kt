package com.yt8492.seihekianalyzerv2.common.scraper

import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.domain.model.Work
import com.yt8492.seihekianalyzerv2.common.domain.model.WorkNameAndUrl

interface DLsiteScraper {
    suspend fun scrapeAllUserBoughtWorkNameAndUrl(loginCookies: Map<String, String>): List<WorkNameAndUrl>
    suspend fun scrapeWorkByUrl(url: Url): Work.OnSale
    suspend fun scrapeAllTodayWorks(): List<Work.OnSale>
}
