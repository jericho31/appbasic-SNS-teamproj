package com.example.appbasic_sns_teamproj

/** intent extra name 용 */
// 오타로 인한 휴먼 에러를 줄일 수 있다고 함.
object Extra {
    const val id = "id"
    const val password = "password"
}

object CurrentUser {
    var user: User? = null
    fun isSignedIn() = user != null
}

object DB {
    val users = mutableMapOf<String, User>(
        // admin 기본으로 넣어둠
        Pair(
            "admin",
            User("admin", "admin", "admin", "admin")
        )
    )
}