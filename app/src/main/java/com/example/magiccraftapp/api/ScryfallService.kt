package com.example.magiccraftapp.api
import com.example.magiccraftapp.model.CardsSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ScryfallService {
    @GET
    suspend fun searchCards(@Url url: String): Response<CardsSearch>
}