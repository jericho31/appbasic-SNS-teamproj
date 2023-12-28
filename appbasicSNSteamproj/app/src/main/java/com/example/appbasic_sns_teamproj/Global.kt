package com.example.appbasic_sns_teamproj

import android.content.Context
import android.content.Intent
import android.widget.ImageView

/** intent extra name 용 */
// 오타로 인한 휴먼 에러를 줄일 수 있다고 함.
//Extra가 겹쳐서 1로 바꿈.

object Extra {
    const val id = "id"
    const val password = "password"

}
object Global {


    object User {
        var isSignedIn = false
        var id = ""
        var isKorean = true
    }

 fun profileChoice(profileImageView: ImageView, context: Context) {
        if (User.isSignedIn) {
            // 로그인 상태일 때
            // 로그인 상태에 따른 아이콘/프로필 이미지 설정
            // 예시: 이미지 리소스를 R.drawable.xxx 로 설정
            profileImageView.setImageResource(R.drawable.snake_sparta)

            // 프로필 이미지 클릭 시 마이페이지로 이동
            profileImageView.setOnClickListener {
                val intent = Intent(context, MyPageActivity::class.java)
                context.startActivity(intent)
            }
        } else {
            // 비로그인 상태일 때
            // 비로그인 상태에 따른 아이콘/프로필 이미지 설정
            // 예시: 이미지 리소스를 R.drawable.yyy 로 설정
            profileImageView.setImageResource(R.drawable.ic_sparta)

            // 프로필 이미지 클릭 시 로그인 페이지로 이동
            profileImageView.setOnClickListener {
                val intent = Intent(context, SignInActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}

//로그인 을해도 똑같이 로그인페이지로 넘어감.


