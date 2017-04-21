package org.shikimori.koshiki.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Александр on 21.04.2017.
 */
interface ShikiApi {

    @GET("/api/animes")
    fun getAnimesList(@Query("page") page: Int,
                      @Query("limit") limit: Int = 15,
                      @Query("order") order: Int?,
                      @Query("kind") kind: String?,
                      @Query("status") status: String?,
                      @Query("season") season: String?,
                      @Query("rating") rating: String?,
                      @Query("genre") genre: String?,
                      @Query("mylist") myList: String?,
                      @Query("search") search: String?)

}