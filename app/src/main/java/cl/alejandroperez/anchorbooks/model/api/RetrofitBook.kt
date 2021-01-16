package cl.alejandroperez.anchorbooks.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/anchorBooks/"

class RetrofitBook {
    companion object {
        fun retrofitInstance(): ApiBook {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiBook::class.java)
        }
    }
}