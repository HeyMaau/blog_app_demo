package top.manpok.blog

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import top.manpok.blog.controller.UserApi
import top.manpok.blog.model.ScanQRCodeResult
import top.manpok.blog.utils.RetrofitManager
import top.manpok.blog.utils.SPUtil

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            IntentIntegrator(this).setCaptureActivity(ScanActivity::class.java)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setPrompt("请扫描二维码")
                .setCameraId(0).setBeepEnabled(true).setBarcodeImageEnabled(false).initiateScan()
        }
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            val contents = result.contents
            if (TextUtils.isEmpty(contents)) {
                Log.d(TAG, "scan cancel")
            } else {
                if (contents.startsWith(Constants.QR_CODE_LOGIN_URL, false)) {
                    val qrCodeCode = contents.replace(Constants.QR_CODE_LOGIN_URL, "")
                    scanQRCode(qrCodeCode)
                    Log.d(TAG, "qrCodeCode: $qrCodeCode")
                }
                Log.d(TAG, "scan result: " + result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    private fun scanQRCode(code: String) {
        val request = RetrofitManager.getRetrofit().create(UserApi::class.java)
        val tokenKey = SPUtil.getUserInfoSP().getString(Constants.SP_KEY_USER_TOKEN, "");
        val tokenMap = mapOf(Constants.SP_KEY_USER_TOKEN to tokenKey)
        val call = request.scanQRCode(code, tokenMap)
        call.enqueue(object : Callback<ScanQRCodeResult> {
            override fun onResponse(
                call: Call<ScanQRCodeResult>,
                response: Response<ScanQRCodeResult>
            ) {
                val scanQRCodeResult = response.body()
                if (scanQRCodeResult != null) {
                    if (scanQRCodeResult.code == 20000) {
                        val intent = Intent(this@MainActivity, LoginConfirmActivity::class.java)
                        intent.putExtra("user", scanQRCodeResult.data)
                        startActivity(intent)
                    }
                }
                Log.d(TAG, "result: $scanQRCodeResult")
            }

            override fun onFailure(call: Call<ScanQRCodeResult>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })
    }
}
