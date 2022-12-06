package top.manpok.blog.model

data class BaseSuccessResult(
    var code: Int,
    var `data`: Any,
    var message: String,
    var success: Boolean
)