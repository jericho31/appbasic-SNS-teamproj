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

class DetailPageActivity : AppCompatActivity() {
    private var likeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        setSlide(Direction.UP, Direction.STAY)

        // UI 요소 초기화
        val btnBack: Button = findViewById(R.id.btnBack)
        val dtAppTrack: TextView = findViewById(R.id.dtAppTrack)
        val buttonLike = findViewById<Button>(R.id.buttonLike)
        val textViewLikeCount = findViewById<TextView>(R.id.textViewLikeCount)
        val username = intent.getStringExtra("username")

        // 좋아요 버튼 클릭 이벤트
        buttonLike.setOnClickListener {
            // 좋아요 수 증가
            likeCount++

            // 새로운 좋아요 수로 UI 업데이트
            textViewLikeCount.text = "$likeCount"

        }
// Intent에서 extra 데이터 가져오기
// 가져온 데이터를 화면에 표시
        dtAppTrack.text = username

        // 뒤로가기 버튼 클릭 이벤트
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

        // 뒤로가기 버튼을 누를 때 슬라이드 효과 적용
        setSlide(Direction.STAY, Direction.UP)
    }
}




