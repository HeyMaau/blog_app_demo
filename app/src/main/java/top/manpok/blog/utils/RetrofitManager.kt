package top.manpok.blog.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import top.manpok.blog.Constants

class RetrofitManager {

    companion object {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}