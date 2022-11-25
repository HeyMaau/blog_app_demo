package top.manpok.blog.model

data class LoginResult(
    val code: Int,
    val `data`: Data,
    val message: String,
    val success: Boolean
)

data class Data(
    val token_key: String
)