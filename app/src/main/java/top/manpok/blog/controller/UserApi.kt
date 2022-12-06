package top.manpok.blog.controller

import retrofit2.Call
import retrofit2.http.*
import top.manpok.blog.model.BaseSuccessResult
import top.manpok.blog.model.BlogUser
import top.manpok.blog.model.LoginResult
import top.manpok.blog.model.ScanQRCodeResult

interface UserApi {

    @POST("user/login/{captcha_key}/{captcha_code}")
    fun login(
        @Path("captcha_key") captchaKey: String,
        @Path("captcha_code") captchaCode: String,
        @Body user: BlogUser
    ): Call<LoginResult>

    @PUT("qr_code/enquire/{code}")
    fun scanQRCode(
        @Path("code") code: String,
        @Body tokenMap: Map<String, String?>
    ): Call<ScanQRCodeResult>

    @GET("qr_code/confirm/{code}")
    fun confirmQRCode(@Path("code") code: String?): Call<BaseSuccessResult>
}