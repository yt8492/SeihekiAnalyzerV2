package com.yt8492.seihekianalyzerv2.server.adapter.scraper

import com.yt8492.serihekianalyzerv2.common.JsoupUtils
import com.yt8492.serihekianalyzerv2.common.domain.model.Tag
import com.yt8492.serihekianalyzerv2.common.domain.model.Work
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.*

object DLsiteScraperWithJsoup {
    suspend fun scrapeByUrl(url: URL): Work {
        val workPage = withContext(Dispatchers.IO) {
            JsoupUtils.requestByGet(url).parse()
        }
        val rows = workPage.getElementById("work_outline").select("tr")
        val tags =  rows.find { row ->
            row.child(0).text() == "ジャンル"
        }?.getElementsByClass("main_genre")
            ?.select("[href]")
            ?.text()?.split(" ".toRegex())
            ?.map { Tag(it) }
            ?.toSet()
            ?: setOf()
        return Work(url, tags)
    }

    suspend fun scrapeTodayWorks(): Set<Work> {
        val today = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"))
        val todayYMD = today.let { "${it.get(Calendar.YEAR)}-${it.get(Calendar.MONTH) + 1}-${it.get(Calendar.DATE)}" }
        val page = withContext(Dispatchers.IO) {
            val latestWorksUrl = URL("https://www.dlsite.com/maniax/new/=/date/$todayYMD/work_type%5B0%5D/SOU")
            JsoupUtils.requestByGet(latestWorksUrl).parse()
        }
        val works = page.getElementsByClass("work_2col")
            .map { element ->
                val url = element.getElementsByClass("work_name").first().let { e ->
                    try {
                        e.select("[href]").toString().split("\"")[1]
                    } catch (error: IndexOutOfBoundsException) {
                        ""
                    }
                }.let { URL(it) }
                val tags = element.getElementsByClass("search_tag")
                    .first()
                    .getElementsByTag("a")
                    .map { Tag(it.text()) }
                    .toSet()
                Work(url, tags)
            }.toSet()
        return works
    }
}