package top.manpok.blog

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import top.manpok.blog.controller.UserApi
import top.manpok.blog.model.BaseSuccessResult
import top.manpok.blog.model.BlogUser
import top.manpok.blog.utils.RetrofitManager

class LoginConfirmActivity : AppCompatActivity() {

    private lateinit var ivAvatar: ImageView
    private lateinit var tvUserName: TextView
    private lateinit var btnConfirm: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_confirm)
        ivAvatar = findViewById(R.id.iv_avatar)
        tvUserName = findViewById(R.id.tv_username)
        btnConfirm = findViewById(R.id.btn_confirm)
        btnCancel = findViewById(R.id.btn_cancel)
        initListener()
        setData()
    }

    private fun initListener() {
        btnConfirm.setOnClickListener {
            val request = RetrofitManager.getRetrofit().create(UserApi::class.java)
            val code = intent.getStringExtra("code")
            request.confirmQRCode(code).enqueue(object : Callback<BaseSuccessResult> {
                override fun onResponse(
                    call: Call<BaseSuccessResult>,
                    response: Response<BaseSuccessResult>
                ) {
                    val result = response.body()
                    if (result?.code == 20000) {
                        Toast.makeText(this@LoginConfirmActivity, "扫码登录成功", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    } else {
                        Toast.makeText(this@LoginConfirmActivity, "扫码登录失败", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<BaseSuccessResult>, t: Throwable) {
                    Toast.makeText(this@LoginConfirmActivity, "扫码登录失败", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
        }
        btnCancel.setOnClickListener { finish() }
    }

    private fun setData() {
        val user = intent.getParcelableExtra<BlogUser>("user")
        Glide.with(this).load(user?.avatar).into(ivAvatar)
        tvUserName.text = user?.userName
    }
}