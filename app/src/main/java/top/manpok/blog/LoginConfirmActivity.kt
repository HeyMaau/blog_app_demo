package top.manpok.blog

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import top.manpok.blog.model.BlogUser

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
        btnConfirm.setOnClickListener { }
        btnCancel.setOnClickListener { finish() }
    }

    private fun setData() {
        val user = intent.getParcelableExtra<BlogUser>("user")
        Glide.with(this).load(user?.avatar).into(ivAvatar)
        tvUserName.text = user?.userName
    }
}