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
import com.example.appbasic_sns_teamproj.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    private val tvId:TextView by lazy { findViewById<TextView>(R.id.txt_id) }
    private val tvTrack:TextView by lazy { findViewById<TextView>(R.id.txt_track) }
    private lateinit var tvTrackName: TextView
    private lateinit var tvWritting: TextView
    private lateinit var binding: ActivityMyPageBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 객체에서 정보 받아오기
        if (!CurrentUser.isSignedIn()) {
            Toast.makeText(this, "오류: 로그인 되어있지 않습니다.", Toast.LENGTH_SHORT).show()
        } else {
            val user = CurrentUser.user
            tvId.text = "${getString(R.string.txt_id)} : " + (CurrentUser.user?.id ?: "not signed in")
            tvTrack.text = "${getString(R.string.txt_track)} : " + (CurrentUser.user?.track ?: "not signed in")
        }

        // 프로필사진 분류
        val ivProfil = findViewById<ImageView>(R.id.iv_profil)
        val image = when (CurrentUser.user?.track) {
            "Android" -> R.drawable.sparta
            "iOS" -> R.drawable.sparta2
            "Unity" -> R.drawable.sparta3
            else -> R.drawable.snake_sparta
        }
        ivProfil.setImageDrawable(ResourcesCompat.getDrawable(resources, image, null))

        // 자신의 트랙에 따라서 보여지는 트랙과 게시글 제목 분류
        tvTrackName = findViewById(R.id.txt_apptrack)
        tvTrackName.text = CurrentUser.user?.track

        tvWritting = findViewById(R.id.txt_writing)
        tvWritting.text = when(CurrentUser.user?.track) {
            "Android" -> "스파르타 친구들 새해 잘 보내!"
            "iOS" -> "코딩 너무 어려워.."
            "Unity" -> "UNITY 팁 몇가지 알려줄게!"
            else -> "최초 AI의 탄생 알고있어??"
        }

        // 버튼 클릭
//        val btnLanguageSetting = findViewById<Button>(R.id.btn_languageSetting)
//        val btnBackToMain = findViewById<ImageView>(R.id.iv_return)
//        val btnGoToDetail = findViewById<Button>(R.id.btn_goDetail)
//        val btnLogout = findViewById<Button>(R.id.btn_logout)

        binding.btnLanguageSetting.setOnClickListener{
            val intent = Intent(this, LanguageSettingActivity::class.java)
            startActivity(intent)
        }

        binding.ivReturn.setOnClickListener{
            finish()
        }

        binding.btnGoDetail.setOnClickListener{
            val intent = Intent(this, DetailPageActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener{
            CurrentUser.user = null
            finish()
        }

    }
}