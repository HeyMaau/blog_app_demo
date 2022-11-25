package top.manpok.blog.base

import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {

        lateinit var context: Context

        fun getAppContext(): Context {
            return context
        }
    }
}