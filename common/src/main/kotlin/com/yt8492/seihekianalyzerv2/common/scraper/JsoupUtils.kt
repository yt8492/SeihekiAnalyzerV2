package com.yt8492.seihekianalyzerv2.common.scraper

import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import org.jsoup.Connection
import org.jsoup.Jsoup

object JsoupUtils {
    fun requestByGet(url: Url, cookies: Map<String, String>? = null, data: Map<String, String>? = null): Connection.Response {
        var connection =
            getConnection(url)
        if (cookies != null) {
            connection = connection.cookies(cookies)
        }
        if (data != null) {
            connection = connection.data(data)
        }
        return connection.method(Connection.Method.GET).execute()
    }

    fun requestByPost(url: Url, cookies: Map<String, String>? = null, data: Map<String, String>? = null): Connection.Response {
        var connection =
            getConnection(url)
        if (cookies != null) {
            connection = connection.cookies(cookies)
        }
        if (data != null) {
            connection = connection.data(data)
        }
        return connection.method(Connection.Method.POST).execute()
    }

    fun getConnection(url: Url): Connection {
        Thread.sleep(1000)
        return Jsoup.connect(url.value).timeout(100000).maxBodySize(0)
    }
}
