package cl.alejandroperez.anchorbooks.model.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiBook {

    @GET("books")
    fun getAllBook() : Call<List<Books>>

    @GET("bookDetail/{id}")
    fun getAllDetail(@Path ("id") id: Int) :  Call<BookDetail>
}