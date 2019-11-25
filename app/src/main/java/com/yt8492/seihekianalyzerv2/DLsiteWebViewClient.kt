package com.yt8492.seihekianalyzerv2

import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient

class DLsiteWebViewClient(
    private val cookieManager: CookieManager,
    private val onLoginSucceed: (Map<String, String>) -> Unit
) : WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        if (url == URL_WELCOME) {
            val cookies = cookieManager.getCookie(url).split(""";\s*""".toRegex()).associate {
                val keyValue = it.split("=")
                keyValue[0] to keyValue[1]
            }
            val phpSessId = cookies["PHPSESSID"]
            Log.d("hogehoge", "PHPSESSID=$phpSessId")
            onLoginSucceed(cookies)
        }
    }

    companion object {
        private const val URL_WELCOME = "https://login.dlsite.com/guide/welcome"
    }
}