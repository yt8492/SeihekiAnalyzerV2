package com.yt8492.seihekianalyzerv2.ui.analyze

import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient

class DLsiteWebViewClient(
    private val cookieManager: CookieManager,
    private val onLoginSucceed: (Map<String, String>) -> Unit
) : WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        if (url.isNullOrBlank()) {
            return
        }
        if (url == URL_WELCOME || url.startsWith("https://ssl.dlsite.com/maniax/login/finish/")) {
            val cookies = cookieManager.getCookie(URL_WELCOME).split(""";\s*""".toRegex()).associate {
                val keyValue = it.split("=")
                keyValue[0] to keyValue[1]
            }.filter {
                requireKeys.contains(it.key)
            }
            Log.d("hogehoge", cookies.map { "${it.key}=${it.value}" }.joinToString())
            onLoginSucceed(cookies)
        }
    }

    companion object {
        private const val URL_WELCOME = "https://login.dlsite.com/guide/welcome"
        private val requireKeys = listOf("XSRF-TOKEN", "PHPSESSID", "jsessionid")
    }
}