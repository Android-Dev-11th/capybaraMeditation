package com.example.capybarameditation.api

import com.example.capybarameditation.models.QuoteObj
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuoteService {
    @GET("random")
    fun getQuoteData(): Call<List<QuoteObj>>


}