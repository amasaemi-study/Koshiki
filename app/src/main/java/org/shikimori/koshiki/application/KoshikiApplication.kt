package org.shikimori.koshiki.application

import android.app.Application

import org.shikimori.koshiki.data.network.api.ShikiApi
import org.shikimori.koshiki.utils.ConstantManager

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Александр on 21.04.2017.
 */
object KoshikiApplication : Application() {

    private val mShikimoriRetrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(ConstantManager.SHIKI_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build() }

    val shikiApi: ShikiApi by lazy { mShikimoriRetrofit.create(ShikiApi::class.java) }
}