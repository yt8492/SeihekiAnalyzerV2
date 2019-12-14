package com.yt8492.serihekianalyzerv2.common

import org.jsoup.Connection
import org.jsoup.Jsoup

object JsoupUtils {
    fun requestByGet(url: String, cookies: Map<String, String>? = null, data: Map<String, String>? = null): Connection.Response {
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

    fun requestByPost(url: String, cookies: Map<String, String>? = null, data: Map<String, String>? = null): Connection.Response {
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

    fun getConnection(url: String, sleepTimeMilliSec: Long = 0): Connection {
        Thread.sleep(sleepTimeMilliSec)
        return Jsoup.connect(url).timeout(100000).maxBodySize(0)
    }
}