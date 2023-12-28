package com.example.appbasic_sns_teamproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val btnBack: Button = findViewById(R.id.btnBack)

        val dtAppTrack: TextView = findViewById(R.id.dtAppTrack)


        val username = intent.getStringExtra("username")
        // Intent에서 extra 데이터 가져오기
        // 가져온 데이터를 화면에 표시
        dtAppTrack.text = username


        btnBack.setOnClickListener {
            // 뒤로가기 클릭시 메인으로 이동
            val intent = Intent(this@DetailPageActivity, MainPageActivity::class.java)
            startActivity(intent)
        }
    }
}