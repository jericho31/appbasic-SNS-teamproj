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

        this.setSlide(Direction.UP, Direction.STAY)

        val btnBack: Button = findViewById(R.id.btnBack)
        val dtAppTrack: TextView = findViewById(R.id.dtAppTrack)
        val buttonLike = findViewById<Button>(R.id.buttonLike)
        val textViewLikeCount = findViewById<TextView>(R.id.textViewLikeCount)
        val username = intent.getStringExtra("username")

// 코드로 UI내에 text 수정
//        Log.d("Track", "track = $username")
//        val txt_title = findViewById<TextView>(R.id.textView11)
//        val txt_detail = findViewById<TextView>(R.id.textView13)
//        val txt_track1 = findViewById<TextView>(R.id.textView15)
//        val txt_track2 = findViewById<TextView>(R.id.textView18)
//        when {
//            username == "Android" -> {
//                txt_title.setText("스파르타 친구들 새해 잘 보내!")
//                txt_detail.setText("다들 이번주도 고생했어 Android 화이팅!")
//            }
//
//            username == "IOS" -> {
//                txt_title.setText("코딩 너무 어려워..")
//                txt_detail.setText("코딩하는데 어떻게 접근해야할 지 모르겠어.. 간단한 팁 같은거 없을까?..")
//            }
//
//            username == "Unity" -> {
//                txt_title.setText("UNITY 팁 하나 알려줄게!")
//                txt_detail.setText("줄이동은 Alt + arrow Key로 할 수 있어!")
//            }
//
//            username == "AI" -> {
//                txt_title.setText("최초 AI의 탄생 알고있어??")
//                txt_detail.setText("알고 있는 사람은 댓글로 공유해줘~")
//            }
//        }


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

    override fun onBackPressed() {
        super.onBackPressed()

        setSlide(Direction.STAY, Direction.UP)
        // 뒤로가기 버튼 말고 휴대폰에서 Back 버튼 해야 함.
    }
}




