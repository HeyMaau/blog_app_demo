package top.manpok.blog.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class BlogUser(
    val userName: String?,
    val password: String?,
    val avatar: String?,
    val email: String?,
    val id: String?,
    val loginIP: String?,
    val regIP: String?,
    val roles: String?,
    val sign: String?,
    val state: String?,
    val createTime: Date?,
    val updateTime: Date?
) : Parcelable {
    constructor(userName: String, password: String) : this(
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