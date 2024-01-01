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

object MemberManager {
    private val users = mutableMapOf<String, User>(
        // admin 기본으로 넣어둠. 각 트랙별로도.
        Pair(
            "admin",
            User("admin", "admin", "admin", "admin")
        ),
        Pair(
            "and@gmail.com",
            User("and@gmail.com", "asdfasdf-0", "김안드", "Android")
        ),
        Pair(
            "ios@gmail.com",
            User("ios@gmail.com", "asdfasdf-0", "이오스", "iOS")
        ),
        Pair(
            "unity@gmail.com",
            User("unity@gmail.com", "asdfasdf-0", "박유닛", "Unity")
        ),
        Pair(
            "etc@gmail.com",
            User("etc@gmail.com", "asdfasdf-0", "최기타", "기타")
        ),
    )

    fun addMember(user: User) {
        users[user.id] = user
    }

    fun getMember(id: String) = users[id]
    fun removeMember(id: String) = users.remove(id)
}