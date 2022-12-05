package top.manpok.blog.model

data class BlogUser(
    val userName: String,
    val password: Any,
    val avatar: String?,
    val email: String?,
    val id: String?,
    val loginIP: Any?,
    val regIP: Any?,
    val roles: String?,
    val sign: String?,
    val state: Any?,
    val createTime: Any?,
    val updateTime: Any?
) {
    constructor(userName: String, password: Any) : this(
        userName,
        password,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}