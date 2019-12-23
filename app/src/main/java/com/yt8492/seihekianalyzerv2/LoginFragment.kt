package com.yt8492.seihekianalyzerv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.yt8492.seihekianalyzerv2.databinding.FragmentLoginBinding
import com.yt8492.seihekianalyzerv2.common.scraper.DLsiteScraperWithJsoup
import kotlinx.coroutines.launch

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
                    binding.urls = DLsiteScraperWithJsoup.scrapeAllUserBoughtUrls(loginCookies)
                        .joinToString("\n") {
                            it.value
                        }
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