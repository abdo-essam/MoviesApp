package com.example.moviesapp.data.remote

import com.example.moviesapp.data.model.home.ResponseGenreLIst
import com.example.moviesapp.data.model.home.ResponseMoviesLIst
import retrofit2.Response
import retrofit2.http.GET

interface ApiServises {
    @GET("genres")
    suspend fun genresList(): Response<ResponseGenreLIst>

    @GET("movies")
    suspend fun moviesLastList(): Response<ResponseMoviesLIst>
}