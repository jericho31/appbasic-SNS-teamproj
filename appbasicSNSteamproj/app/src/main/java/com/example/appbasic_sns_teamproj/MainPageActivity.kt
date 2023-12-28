package com.example.appbasic_sns_teamproj

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.appbasic_sns_teamproj.R.layout.activity_main_page


class MainPageActivity : AppCompatActivity() {
    private lateinit var appTrack: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main_page)
//        현재 액티비티의 상태를 복원하고 레이아웃 파일인 activity_main_page를 화면에 표시하는 코드이다.


                appTrack = findViewById(R.id.appTrack)
        val myProfile = findViewById<ImageButton>(R.id.myProfile)
//        appTrack 변수에 ID가 appTrack인 TextView를 할당하고,
//        myProfile 변수에 ID가 myProfile인 ImageButton를 할당한다.

// 로그인 여부에 따라 프로필 이미지 업데이트 및 클릭 액션 설정
                Global.profileChoice(myProfile, this)
//        Global 클래스의 profileChoice 메서드를 호출하여,
//        로그인 여부에 따라 프로필 이미지를 업데이트하고 클릭 액션을 설정한다.
//        myProfile은 프로필 이미지를 나타내는 ImageButton이며,
//        this는 현재 액티비티를 나타냅니다.


        val btnDetail: Button = findViewById(R.id.btnDetail)
        btnDetail.setOnClickListener {
            val appTrackText: String = appTrack.text.toString()
            val intent = Intent(this@MainPageActivity, DetailPageActivity::class.java)
            intent.putExtra("username", appTrackText)
            startActivity(intent)
        }
//        ID가 btnDetail인 Button을 찾아 btnDetail 변수에 할당하고,
    //        해당 버튼에 클릭 리스너를 설정한다. 버튼이 클릭되면
    //        DetailPageActivity로 이동하면서
    //        username이라는 키로 appTrackText 값을 전달한다.


        /*
        val logInButton:Button = findViewById(R.id.logInButton)
//        val btnGood: imageButton = findViewById(R.id.btnGood)
//        goodCount = findViewById(R.id.goodCount)



        val logInButton:Button = findViewById(R.id.button)
        logInButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    */
//        val myProfile: ImageButton = findViewById(R.id.myProfile)
//        myProfile.setOnClickListener {
//    val intent = Intent(this, SignInActivity::class.java)
//    startActivity(intent)
//}

//        val myProfile: ImageButton = findViewById(R.id.myProfile)
//        myProfile.setOnClickListener {
//            val intent = Intent(this, MyPageActivity::class.java)
//            startActivity(intent)
//        }

    }
}


