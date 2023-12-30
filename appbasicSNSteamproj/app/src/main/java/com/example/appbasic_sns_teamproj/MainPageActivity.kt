package com.example.appbasic_sns_teamproj

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import com.example.appbasic_sns_teamproj.R.layout.activity_main_page


class MainPageActivity : AppCompatActivity() {
    private lateinit var appTrack: TextView
    private lateinit var iosTrack: TextView
    private lateinit var unityTrack: TextView
    private lateinit var aiTrack: TextView
    private lateinit var myProfile: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main_page)

//        알림창을 표시하는 함수 호출
        showNotice()

//        XML에서 정의한 각 위젯들을 변수에 연결
        appTrack = findViewById(R.id.appTrack)
        iosTrack = findViewById(R.id.iosTrack)
        unityTrack = findViewById(R.id.unityTrack)
        aiTrack = findViewById(R.id.aiTrack)
        myProfile = findViewById(R.id.myProfile)

//        appTrack 변수에 ID가 appTrack인 TextView를 할당하고,
//        myProfile 변수에 ID가 myProfile인 ImageButton를 할당한다.


//        프로필사진 분류
        val myProfile = findViewById<ImageButton>(R.id.myProfile)
        val image = when (CurrentUser.user?.track) {
            "Android" -> R.drawable.sparta
            "iOS" -> R.drawable.sparta2
            "Unity" -> R.drawable.sparta3
            else -> R.drawable.snake_sparta
        }
        myProfile.setImageDrawable(ResourcesCompat.getDrawable(resources, image, null))


        // 각 트랙에 대한 상세 페이지로 이동하는 버튼에 클릭 리스너 설정
        // 글을 누르면 글에 대한 디테일 페이지로 이동

        val btnDetail: Button = findViewById(R.id.btnDetail)
        btnDetail.setOnClickListener {
            val appTrackText: String = appTrack.text.toString()
            val intent = Intent(this@MainPageActivity, DetailPageActivity::class.java)
            intent.putExtra("username", appTrackText)
            startActivity(intent)
        }


        val btnDetail2: Button = findViewById(R.id.btnDetail2)
        btnDetail2.setOnClickListener {
            val iosTrackText: String = iosTrack.text.toString()
            val intent = Intent(this@MainPageActivity, DetailPageActivity2::class.java)
            intent.putExtra("username", iosTrackText)
            startActivity(intent)
        }

        val btnDetail3: Button = findViewById(R.id.btnDetail3)
        btnDetail3.setOnClickListener {
            val unityTrackText: String = unityTrack.text.toString()
            val intent = Intent(this@MainPageActivity, DetailPageActivity3::class.java)
            intent.putExtra("username", unityTrackText)
            startActivity(intent)
        }

        val btnDetail4: Button = findViewById(R.id.btnDetail4)
        btnDetail4.setOnClickListener {
            val aiTrackText: String = aiTrack.text.toString()
            val intent = Intent(this@MainPageActivity, DetailPageActivity4::class.java)
            intent.putExtra("username", aiTrackText)
            startActivity(intent)
        }
//        ID가 btnDetail인 Button을 찾아 btnDetail 변수에 할당하고,
//        해당 버튼에 클릭 리스너를 설정한다. 버튼이 클릭되면
//        DetailPageActivity로 이동하면서
//        username이라는 키로 appTrackText 값을 전달한다.

//        myProfile 이미지버튼 클릭 리스너 설정
//        프로필 이미지 클릭 시, 로그인 여부에 따라 다른 동작 수행
        myProfile.setOnClickListener {

            if (CurrentUser.isSignedIn()) {
                Log.d("MyApp", "User is signed in: ${CurrentUser.isSignedIn()}")
//                 로그인 상태일 때
//                 이미지 변경은 버튼 클릭할 때가 아니라, 로그인 성공했을 때 해줘야 함.

//                 프로필 이미지 클릭 시 마이페이지로 이동
                val intent = Intent(this, MyPageActivity::class.java)
                startActivity(intent)
            } else {
//                 비로그인 상태일 때
//                 프로필 이미지 클릭 시 로그인 페이지로 이동
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showNotice() {
        val noticeTitle = getString(R.string.notice_title)
        val noticeContent = getString(R.string.notice_content)


        val textView = TextView(this)
        textView.text = "$noticeTitle\n\n$noticeContent"
//         \n 은 줄 바꿈
        textView.textSize = 10F

//         AlertDialog를 사용하여 공지사항을 표시
        AlertDialog.Builder(this)
            .setTitle(noticeTitle)
            .setMessage(noticeContent)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
//                 OK 버튼을 누르면 아무 작업 없이 닫기
                dialog.dismiss()
            })
//             })는 이 함수 블록을 닫는다.
            .show()
    }
}



