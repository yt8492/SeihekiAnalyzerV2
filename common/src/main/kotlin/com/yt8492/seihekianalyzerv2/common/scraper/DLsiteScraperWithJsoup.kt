package com.yt8492.seihekianalyzerv2.common.scraper

import com.yt8492.seihekianalyzerv2.common.domain.model.Tag
import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.domain.model.Work
import com.yt8492.seihekianalyzerv2.common.domain.model.WorkNameAndUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object DLsiteScraperWithJsoup : DLsiteScraper {

    private val URL_USER_BUY_HISTORY = Url("https://ssl.dlsite.com/maniax/mypage/userbuy")

    override suspend fun scrapeAllUserBoughtUrls(loginCookies: Map<String, String>): List<Url> {
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
            Url(it)
        }
    }

    override suspend fun scrapeAllUserBoughtWorkNameAndUrl(loginCookies: Map<String, String>): List<WorkNameAndUrl> {
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
        val userBuyHistoryWorkNameAndUrls = listOf(thisMonthUserBuyHistoryResult, pastMonthUserBuyHistoryResult)
            .flatMap { d ->
                d.getElementsByClass("work_name")
                    .map { e ->
                        val name = e.getElementsByTag("a").text()
                        val url = try {
                            Url(e.select("[href]").toString().split("\"")[1])
                        } catch (error: IndexOutOfBoundsException) {
                            null
                        }
                        WorkNameAndUrl(name, url)
                    }
            }
        return userBuyHistoryWorkNameAndUrls
    }

    override suspend fun scrapeWorkByUrl(url: Url): Work.OnSale {
        val workPage = withContext(Dispatchers.IO) {
            JsoupUtils.requestByGet(url).parse()
        }
        val name = workPage.getElementById("work_name")
            .getElementsByTag("a")
            .text()
        val rows = workPage.getElementById("work_outline").select("tr")
        val tags =  rows.find { row ->
            row.child(0).text() == "ジャンル"
        }?.getElementsByClass("main_genre")
            ?.select("[href]")
            ?.text()?.split(" ".toRegex())
            ?.map { Tag(it) }
            ?: listOf()

        return Work.OnSale(name, url, tags)
    }

    override suspend fun scrapeAllTodayWorks(): List<Work.OnSale> {
        val today = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"))
        val todayYMD = today.let { "${it.get(Calendar.YEAR)}-${it.get(Calendar.MONTH) + 1}-${it.get(Calendar.DATE)}" }
        val page = withContext(Dispatchers.IO) {
            val latestWorksUrl = Url("https://www.dlsite.com/maniax/new/=/date/$todayYMD/work_type%5B0%5D/SOU")
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
                }.let { Url(it) }
                val name = element.getElementsByClass("work_name")
                    .first()
                    .getElementsByTag("a")
                    .text()
                val tags = element.getElementsByClass("search_tag")
                    .first()
                    .getElementsByTag("a")
                    .map { Tag(it.text()) }
                Work.OnSale(name, url, tags)
            }
        return works
    }
}