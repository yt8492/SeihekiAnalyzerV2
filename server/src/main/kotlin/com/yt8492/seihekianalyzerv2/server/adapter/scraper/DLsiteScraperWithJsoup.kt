package com.yt8492.seihekianalyzerv2.server.adapter.scraper

import com.yt8492.serihekianalyzerv2.common.JsoupUtils
import com.yt8492.serihekianalyzerv2.common.domain.model.Tag
import com.yt8492.serihekianalyzerv2.common.domain.model.Work
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.*

object DLsiteScraperWithJsoup {

    private val URL_LOGIN = URL("https://login.dlsite.com/login")
    private val URL_USER_BUY_HISTORY = URL("https://ssl.dlsite.com/maniax/mypage/userbuy")

    suspend fun scrapeAllUserBoughtUrls(loginCookies: Map<String, String>): List<URL> {
        val historyCookies = withContext(Dispatchers.IO) {
            JsoupUtils.requestByPost(URL_USER_BUY_HISTORY, cookies = loginCookies).cookies()
        }

        val thisMonthUserBuyHistoryResult = withContext(Dispatchers.IO) {
            JsoupUtils.requestByGet(URL_USER_BUY_HISTORY, cookies = historyCookies).parse()
        }
        val pastMonthUserBuyHistoryResult = withContext(Dispatchers.IO) {
            JsoupUtils.requestByGet(URL_USER_BUY_HISTORY, cookies = historyCookies, data = mapOf(
                "_layout" to "mypage_userbuy_complete",
                "_form_id" to "mypageUserbuyCompleteForm",
                "_site" to "maniax",
                "_view" to "input",
                "start" to "all"
            )).parse()
        }
        val userBuyHistoryUrls = listOf(thisMonthUserBuyHistoryResult, pastMonthUserBuyHistoryResult)
            .flatMap { d ->
                d.getElementsByClass("work_name")
                    .map { e ->
                        try {
                            e.select("[href]").toString().split("\"")[1]
                        } catch (error: IndexOutOfBoundsException) {
                            ""
                        }
                    }
            }
        return userBuyHistoryUrls.map {
            URL(it)
        }
    }

    suspend fun scrapeWorkByUrl(url: URL): Work {
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
            ?: listOf()

        return Work(url, tags)
    }

    suspend fun scrapeAllTodayWorks(): List<Work> {
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
                Work(url, tags)
            }
        return works
    }
}