package top.manpok.blog.model

data class ScanQRCodeResult(
    val code: Int,
    val `data`: BlogUser,
    val message: String,
    val success: Boolean
)