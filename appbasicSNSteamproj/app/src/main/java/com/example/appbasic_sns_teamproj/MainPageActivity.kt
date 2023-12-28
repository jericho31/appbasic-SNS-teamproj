package com.example.appbasic_sns_teamproj

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.appbasic_sns_teamproj.R.layout.activity_main_page


class MainPageActivity : AppCompatActivity() {
    private lateinit var apptrack: TextView
//    private var goodcount = 0
//    private lateinit var goodCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main_page)

        apptrack = findViewById(R.id.appTrack)
        /*
        val logInButton:Button = findViewById(R.id.logInButton)
//        val btnGood: imageButton = findViewById(R.id.btnGood)
//        goodCount = findViewById(R.id.goodCount)

//        btnGood.setOnClickListener {
//            // 하트 버튼 클릭 시 likeCount 증가 및 텍스트뷰 업데이트
//            goodcount++
//            goodCount.text = goodCount.toString()

        val logInButton:Button = findViewById(R.id.button)
        logInButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    */


        val btnDetail: Button = findViewById(R.id.btnDetail)
        btnDetail.setOnClickListener {
            detail()
            // 글 클릭시 디테일로 이동


        }
    }
    private fun detail() {
        val appTrackText: String = apptrack.text.toString()

        val intent = Intent(this@MainPageActivity, DetailPageActivity::class.java)
        intent.putExtra("username", appTrackText)

//        intent.putExtra("backgroundColor", appTrackText.background as? ColorDrawable)
        //색깔intent
        startActivity(intent)

        }
    }

