package com.example.appbasic_sns_teamproj

/** intent extra name 용 */
// 오타로 인한 휴먼 에러를 줄일 수 있다고 함.
object Extra {
    const val id = "id"
    const val password = "password"
}

object User {
    var isSignedIn = false
    var id = ""
//    var locale = LocaleConfig()
}