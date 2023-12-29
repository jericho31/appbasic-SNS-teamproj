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

class DetailPageActivity3 : AppCompatActivity() {
    private var likeCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page3)

        val btnBack: Button = findViewById(R.id.btnBack)
        val dtAppTrack: TextView = findViewById(R.id.dtAppTrack)
        val buttonLike = findViewById<Button>(R.id.buttonLike)
        val textViewLikeCount = findViewById<TextView>(R.id.textViewLikeCount)

        this.setSlide(Direction.UP, Direction.STAY)


        buttonLike.setOnClickListener {
            // Increment the like count
            likeCount++

            // Update the UI with the new like count
            textViewLikeCount.text = "$likeCount"

        }
        val username = intent.getStringExtra("username")
// Intent에서 extra 데이터 가져오기
// 가져온 데이터를 화면에 표시
        dtAppTrack.text = username
        btnBack.setOnClickListener {
            // 뒤로가기 클릭시 메인으로 이동
            val intent = Intent(this@DetailPageActivity3, MainPageActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()

        setSlide(Direction.STAY, Direction.UP)
        // 뒤로가기 버튼 말고 휴대폰에서 Back 버튼 해야 함.
    }
}




