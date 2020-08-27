package cl.desafiolatam.restapidesafiodos.remote

import cl.desafiolatam.restapidesafiodos.pojo.Photo

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/photos")
    fun getAllPosts(): Call<ArrayList<Photo>>

}