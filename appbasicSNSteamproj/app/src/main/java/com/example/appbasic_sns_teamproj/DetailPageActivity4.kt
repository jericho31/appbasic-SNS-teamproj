package com.example.appbasic_sns_teamproj

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.widget.NestedScrollView

class DetailPageActivity4 : AppCompatActivity() {
    private var likeCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page4)

        val btnBack: Button = findViewById(R.id.btnBack)
        val dtAiTrack: TextView = findViewById(R.id.dtAiTrack)
        val buttonLike = findViewById<Button>(R.id.buttonLike)
        val textViewLikeCount = findViewById<TextView>(R.id.textViewLikeCount)
        val username4 = intent.getStringExtra("username")

        setSlide(Direction.UP, Direction.STAY)

        buttonLike.setOnClickListener {
            // Increment the like count
            likeCount++

            // Update the UI with the new like count
            textViewLikeCount.text = "$likeCount"

        }
// Intent에서 extra 데이터 가져오기
// 가져온 데이터를 화면에 표시
        dtAiTrack.text = username4
        btnBack.setOnClickListener {
            // 뒤로가기 클릭시 메인으로 이동
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    override fun onPause() {
        super.onPause()

        setSlide(Direction.STAY, Direction.UP)
    }
}




