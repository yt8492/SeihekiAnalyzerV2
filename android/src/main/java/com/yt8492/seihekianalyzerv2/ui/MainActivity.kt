package com.yt8492.seihekianalyzerv2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.yt8492.seihekianalyzerv2.ui.analyze.LoginFragment
import com.yt8492.seihekianalyzerv2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
