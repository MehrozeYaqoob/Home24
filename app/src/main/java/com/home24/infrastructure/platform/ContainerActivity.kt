package com.home24.infrastructure.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.home24.R
import kotlinx.android.synthetic.main.activity_main.*

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}
