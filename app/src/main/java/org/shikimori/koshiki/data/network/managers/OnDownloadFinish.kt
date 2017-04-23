package org.shikimori.koshiki.data.network.managers

import retrofit2.Call
import retrofit2.Response

/**
 * Created by Александр on 22.04.2017.
 */
interface OnDownloadFinish {
    /**
     * Метод выполняется при успешной загрузке
     */
    fun onLoadSuccess(call: Call<*>, response: Response<*>)

    /**
     * Метод выполняется при ошибке загрузки
     */
    fun onLoadFailure(throwable: Throwable)
}