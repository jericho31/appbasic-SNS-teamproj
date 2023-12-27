package com.example.appbasic_sns_teamproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.appbasic_sns_teamproj.R.layout.activity_main_page


class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main_page)

        /*
        val logInButton:Button = findViewById(R.id.logInButton)
        logInButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    */
        val btnDetail: Button = findViewById(R.id.btnDetail)

        btnDetail.setOnClickListener {
            // 글 클릭시 디테일로 이동
            val intent = Intent(this@MainPageActivity, DetailPageActivity::class.java)
            startActivity(intent)
            DetailPageActivity()
        }
    }
}

