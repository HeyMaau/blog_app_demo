package top.manpok.blog

class Constants {

    companion object {
        const val BASE_URL: String = "http://192.168.137.23:8080/"
        const val CAPTCHA_URL = BASE_URL + "user/captcha?captcha_key="
        const val SP_TABLE_KEY_USER_INFO = "user_info"
        const val QR_CODE_LOGIN_URL = "http://manpok.top/app/download/"
        const val SP_KEY_USER_TOKEN = "token_key"
    }


}