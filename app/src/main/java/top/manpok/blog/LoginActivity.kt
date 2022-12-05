package top.manpok.blog

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import top.manpok.blog.controller.UserApi
import top.manpok.blog.model.BlogUser
import top.manpok.blog.model.LoginResult
import top.manpok.blog.utils.RetrofitManager
import top.manpok.blog.utils.SPUtil

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    lateinit var etUserName: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var ivCaptcha: ImageView
    lateinit var etCaptcha: EditText

    /**
     * 图灵验证码KEY
     */
    val captchaKey: String = System.currentTimeMillis().toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUserName = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        ivCaptcha = findViewById(R.id.ivCaptcha)
        etCaptcha = findViewById(R.id.etCaptcha)
        Glide.with(this).load(Constants.CAPTCHA_URL + captchaKey).into(ivCaptcha)
        btnLogin.setOnClickListener {
            val userName = etUserName.text.toString()
            val password = etPassword.text.toString()
            val captchaCode = etCaptcha.text.toString()
            doLogin(userName, password, captchaCode)
        }
    }

    private fun doLogin(userName: String, password: String, captchaCode: String) {
        val retrofit = RetrofitManager.getRetrofit()
        val request = retrofit.create(UserApi::class.java)
        val user = BlogUser(userName, password)
        val call = request.login(captchaKey, captchaCode, user)
        call.enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                val loginResult = response.body()
                val edit = SPUtil.getUserInfoSP().edit()
                edit.putString(Constants.SP_KEY_USER_TOKEN, loginResult?.data?.token_key)
                edit.apply()
                Log.d(TAG, "login ----> $loginResult")
                finish()
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.toString()}")
                Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_SHORT)
            }

        })
    }


}