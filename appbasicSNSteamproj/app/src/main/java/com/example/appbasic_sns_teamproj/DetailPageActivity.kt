package com.example.appbasic_sns_teamproj

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.widget.NestedScrollView

class DetailPageActivity : AppCompatActivity() {
    private var likeCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        setSlide(Direction.UP, Direction.STAY)

        val btnBack: Button = findViewById(R.id.btnBack)
        val dtAppTrack: TextView = findViewById(R.id.dtAppTrack)
        val buttonLike = findViewById<Button>(R.id.buttonLike)
        val textViewLikeCount = findViewById<TextView>(R.id.textViewLikeCount)
        val username = intent.getStringExtra("username")



        buttonLike.setOnClickListener {
            // Increment the like count
            likeCount++

            // Update the UI with the new like count
            textViewLikeCount.text = "$likeCount"

        }
// Intent에서 extra 데이터 가져오기
// 가져온 데이터를 화면에 표시
        dtAppTrack.text = username
        btnBack.setOnClickListener {
            // 뒤로가기 클릭시 메인으로 이동
            finish()
        }
    }

    override fun onPause() {
        super.onPause()

        setSlide(Direction.STAY, Direction.UP)
    }
}




