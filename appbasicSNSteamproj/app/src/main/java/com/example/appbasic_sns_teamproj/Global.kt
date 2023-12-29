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

object User {
    var isSignedIn = false
    var id = ""
    var isKorean = true
}


//로그인 을해도 똑같이 로그인페이지로 넘어감.


