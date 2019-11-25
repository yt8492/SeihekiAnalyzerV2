package com.yt8492.seihekianalyzerv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow

class MainActivity : AppCompatActivity() {

    private val loginFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as? LoginFragment
            ?: LoginFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commitNow {
            replace(R.id.mainFragmentContainerView, loginFragment)
        }
    }
}
