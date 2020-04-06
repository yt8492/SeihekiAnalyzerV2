package com.yt8492.seihekianalyzerv2.ui.analyze

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.yt8492.seihekianalyzerv2.R
import com.yt8492.seihekianalyzerv2.databinding.FragmentLoginBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var analyzeViewModelProvider: AnalyzeViewModelProvider

    private val viewModel by activityViewModels<AnalyzeViewModel> {
        analyzeViewModelProvider
    }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginWebView.apply {
            settings.apply {
                allowUniversalAccessFromFileURLs = true
            }
            val cookieManager = CookieManager.getInstance().apply {
                setAcceptCookie(true)
            }
            val webClient = DLsiteWebViewClient(cookieManager) { loginCookies ->
                viewModel.analyze(loginCookies)
                findNavController().navigate(R.id.action_login_to_analyze_result)
            }
            webViewClient = webClient
            loadUrl(URL_LOGIN)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.loginWebView.apply {
            stopLoading()
            webViewClient = null
            destroy()
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    companion object {
        private const val URL_LOGIN = "https://login.dlsite.com/login"

        fun newInstance(): LoginFragment =
            LoginFragment()
    }
}
