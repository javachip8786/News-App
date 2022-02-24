package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

class start_image : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_image)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@start_image,LoginActivity::class.java)
            startActivity(intent)
            finish()
        },1000)
    }
}