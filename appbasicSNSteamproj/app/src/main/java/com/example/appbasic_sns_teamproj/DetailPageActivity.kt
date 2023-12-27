package com.example.appbasic_sns_teamproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val btnBack: Button = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            // 뒤로가기 클릭시 메인으로 이동
            val intent = Intent(this@DetailPageActivity, MainPageActivity::class.java)
            startActivity(intent)
            MainPageActivity()
        }
    }
}