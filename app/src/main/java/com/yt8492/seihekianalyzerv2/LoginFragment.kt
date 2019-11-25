package com.yt8492.seihekianalyzerv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.yt8492.seihekianalyzerv2.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )
        binding.loginWebView.apply {
            settings.apply {
                allowUniversalAccessFromFileURLs = true
            }
            val cookieManager = CookieManager.getInstance().apply {
                setAcceptCookie(true)
            }
            val webClient = DLsiteWebViewClient(cookieManager) { loginCookies ->
                visibility = View.GONE
                lifecycleScope.launch {
                    val userBuyHistoryUrls = withContext(Dispatchers.IO) {
                        val historyCookies = JsoupUtils.requestByPost(URL_USER_BUY_HISTORY, cookies = loginCookies).cookies()

                        val thisMonthUserBuyHistoryResult = JsoupUtils.requestByGet(URL_USER_BUY_HISTORY, cookies = historyCookies).parse()
                        val pastMonthUserBuyHistoryResult = JsoupUtils.requestByGet(URL_USER_BUY_HISTORY, cookies = historyCookies, data = mapOf(
                            "_layout" to "mypage_userbuy_complete",
                            "_form_id" to "mypageUserbuyCompleteForm",
                            "_site" to "maniax",
                            "_view" to "input",
                            "start" to "all"
                        )).parse()
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
                        userBuyHistoryUrls
                    }
                    binding.urls = userBuyHistoryUrls.joinToString("\n")
                }
            }
            webViewClient = webClient
            loadUrl(URL_LOGIN)
        }
        return binding.root
    }

    companion object {
        private const val URL_LOGIN = "https://login.dlsite.com/login"
        private const val URL_USER_BUY_HISTORY = "https://ssl.dlsite.com/maniax/mypage/userbuy"

        fun newInstance(): LoginFragment = LoginFragment()
    }
}