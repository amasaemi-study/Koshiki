package org.shikimori.koshiki.data.network.api

import org.shikimori.koshiki.data.network.models.pojo.AnimeListPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Александр on 21.04.2017.
 */
interface ShikiApi {

    @GET("/api/animes?limit=14")
    fun getAnimesList(@Query("page") page: Int,
                      @Query("order") order: String?,
                      @Query("type") kind: String?,
                      @Query("status") status: String?,
                      @Query("season") season: String?,
                      @Query("rating") rating: String?,
                      @Query("genre") genre: String?,
                      @Query("mylist") myList: String?,
                      @Query("search") search: String?): Call<List<AnimeListPojo>>

}