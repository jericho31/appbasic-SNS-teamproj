package com.example.appbasic_sns_teamproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        val tv_track = findViewById<TextView>(R.id.txt_track)
        val tv_id = findViewById<TextView>(R.id.txt_id)
        if (intent.hasExtra("track")) {
            tv_track.text = "트랙 : " + intent.getStringExtra("track")
        }
        if (intent.hasExtra("id")) {
            tv_id.text = "아이디 : " + intent.getStringExtra("id")

        }

        val iv_profil = findViewById<ImageView>(R.id.iv_profil)
//        val image = when (tv_track) {
//            android ->
//            ios ->
//            unity ->
//            else ->
//        }
//        iv_profil.setImageDrawable(ResourcesCompat.getDrawable(resources, image, null))


        val btnLanguageSetting = findViewById<Button>(R.id.btn_languageSetting)
        val btnBackToMain = findViewById<ImageView>(R.id.iv_return)
        val btnGoToDetail = findViewById<Button>(R.id.btn_goDetail)

        btnLanguageSetting.setOnClickListener {
            val intent = Intent(this, LanguageSettingActivity::class.java)
            startActivity(intent)
        }

        btnBackToMain?.setOnClickListener {
            finish()
        }

        btnGoToDetail.setOnClickListener {
            val intent = Intent(this, DetailPageActivity::class.java)
            startActivity(intent)
        }

        }
    }
