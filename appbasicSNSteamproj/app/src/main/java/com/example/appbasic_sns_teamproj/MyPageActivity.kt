package com.example.appbasic_sns_teamproj

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat

class MyPageActivity : AppCompatActivity() {
    private val tvId:TextView by lazy { findViewById<TextView>(R.id.txt_id) }
    private val tvTrack:TextView by lazy { findViewById<TextView>(R.id.txt_track) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        // 객체에서 정보 받아오기
        if (!CurrentUser.isSignedIn()) {
            Toast.makeText(this, "오류: 로그인 되어있지 않습니다.", Toast.LENGTH_SHORT).show()
        } else {
            val user = CurrentUser.user
            tvId.text = "아이디 : " + (CurrentUser.user?.id ?: "not signed in")
            tvTrack.text = "트랙 : " + (CurrentUser.user?.track ?: "not signed in")
        }


        // 프로필사진 분류 (생각해보니 이걸 굳이 여기서 랜덤을 줄 필요가 없네)
//        val iv_profil = findViewById<ImageView>(R.id.iv_profil)
//        val image = when ((1..3).random()) {
//            1 -> R.drawable.sparta
//            2 -> R.drawable.sparta2
//            else -> R.drawable.sparta3
//        }
//        iv_profil.setImageDrawable(ResourcesCompat.getDrawable(resources, image, null))

        // 자신의 트랙에 따라서 보여지는 트랙과 게시글 제목 분류
//        val tv_trackName = findViewById<TextView>(R.id.txt_apptrack)
//        val tv_writting = findViewById<TextView>(R.id.txt_writing)
//        tv_trackName.text = intent.getStringExtra("track")
//        tv_writting.text = when(intent.getStringExtra("track")) {
//            android -> "스파르타 친구들 새해 잘 보내!"
//            ios -> "코딩 너무 어려워.."
//            else -> "UNITY 팁 몇가지 알려줄게!"
//        }

        // 버튼 클릭
        val btnLanguageSetting = findViewById<Button>(R.id.btn_languageSetting)
        val btnBackToMain = findViewById<ImageView>(R.id.iv_return)
        val btnGoToDetail = findViewById<Button>(R.id.btn_goDetail)

        btnLanguageSetting.setOnClickListener{
            val intent = Intent(this, LanguageSettingActivity::class.java)
            startActivity(intent)
        }

        btnBackToMain.setOnClickListener{
            finish()
        }

        btnGoToDetail.setOnClickListener{
            val intent = Intent(this, DetailPageActivity::class.java)
            startActivity(intent)
        }

    }
}