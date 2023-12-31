package com.example.appbasic_sns_teamproj

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
//        현재 액티비티의 상태를 복원하고 레이아웃 파일인 activity_main_page를 화면에 표시하는 코드이다.
        showNotice()

        appTrack = findViewById(R.id.appTrack)
        iosTrack = findViewById(R.id.iosTrack)
        unityTrack = findViewById(R.id.unityTrack)
        aiTrack = findViewById(R.id.aiTrack)




        myProfile = findViewById(R.id.myProfile)
//        appTrack 변수에 ID가 appTrack인 TextView를 할당하고,
//        myProfile 변수에 ID가 myProfile인 ImageButton를 할당한다.


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

        // myProfile 이미지버튼 클릭 리스너 설정
        myProfile.setOnClickListener {

            if (CurrentUser.isSignedIn()) {
                Log.d("MyApp", "User is signed in: ${CurrentUser.isSignedIn()}")
                // 로그인 상태일 때
                // 이미지 변경은 버튼 클릭할 때가 아니라, 로그인 성공했을 때 해줘야 함.
                // onResume에 일단 만듦.

                // 프로필 이미지 클릭 시 마이페이지로 이동
                val intent = Intent(this, MyPageActivity::class.java)
                startActivity(intent)
            } else {
                // 비로그인 상태일 때
                // 프로필 이미지 클릭 시 로그인 페이지로 이동
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
        // \n 은 줄 바꿈
        textView.textSize = 10F

        // AlertDialog를 사용하여 공지사항을 표시
        AlertDialog.Builder(this)
            .setTitle(noticeTitle)
            .setMessage(noticeContent)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                // OK 버튼을 누르면 아무 작업 없이 닫기
                dialog.dismiss()
            })
            .show()
    }

    override fun onResume() {
        super.onResume()

        // 로그인 페이지에서 돌아와서 로그인이 성공했으면 이미지 변경.
        // 정석은 registerLauncher.launch(intent) 해서 로그인 성공 여부를 돌려받고 해야함.
        if (CurrentUser.isSignedIn()) myProfile.setImageResource(R.drawable.sparta3)
    }

}



