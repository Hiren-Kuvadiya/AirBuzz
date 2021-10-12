package com.test.airbuzz.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.test.airbuzz.R

class SplashActivity : AppCompatActivity() {

    private val TIME_DELAY: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        gotoMainScreen()

    }

    private fun gotoMainScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val mIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mIntent)
            finish()
        }, TIME_DELAY)
    }

}