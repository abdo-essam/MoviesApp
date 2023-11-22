package com.example.moviesapp.data.repository

import com.example.moviesapp.data.remote.ApiServises
import javax.inject.Inject


class HomeRepository @Inject constructor(private val api: ApiServises) {

    suspend fun genresList() = api.genresList()
    suspend fun lastMoviesList() = api.moviesLastList()

}