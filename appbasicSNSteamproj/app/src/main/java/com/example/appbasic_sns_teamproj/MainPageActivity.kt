package com.example.appbasic_sns_teamproj

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.appbasic_sns_teamproj.R.layout.activity_main_page


class MainPageActivity : AppCompatActivity() {
//    private var goodcount = 0
//    private lateinit var goodCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main_page)

//        val btnGood: imageButton = findViewById(R.id.btnGood)
//        goodCount = findViewById(R.id.goodCount)

//        btnGood.setOnClickListener {
//            // 하트 버튼 클릭 시 likeCount 증가 및 텍스트뷰 업데이트
//            goodcount++
//            goodCount.text = goodCount.toString()
            /*
        val logInButton:Button = findViewById(R.id.logInButton)
        logInButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    */
        }
    }
}