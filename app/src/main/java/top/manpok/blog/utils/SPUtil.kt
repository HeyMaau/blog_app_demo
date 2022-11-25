package top.manpok.blog.utils

import android.content.Context
import android.content.SharedPreferences
import top.manpok.blog.Constants
import top.manpok.blog.base.BaseApplication

class SPUtil {

    companion object {
        fun getUserInfoSP(): SharedPreferences {
            return BaseApplication.getAppContext()
                .getSharedPreferences(Constants.SP_TABLE_KEY_USER_INFO, Context.MODE_PRIVATE)
        }
    }
}