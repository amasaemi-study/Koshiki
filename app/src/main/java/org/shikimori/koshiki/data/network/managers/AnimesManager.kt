package org.shikimori.koshiki.managers

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import org.shikimori.koshiki.application.KoshikiApplication
import org.shikimori.koshiki.data.network.managers.OnDownloadFinish
import org.shikimori.koshiki.data.network.models.pojo.AnimeListPojo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Менеджер для работы с Animes API
 */

class AnimesManager(val progressBar: ProgressBar?) {

    lateinit private var listener: OnDownloadFinish

    fun setOnDownloadFinishListener(listener: OnDownloadFinish) {
        this.listener = listener
    }

    /**
     * GET запрос /api/animes
     * page - страница, целое число
     * limit - количество элементов на странице (по умолчанию - 15)
     * order - сортировка, возможно null
     * kind - тип аниме, возможно null
     * status - статус аниме, возможно null
     * season - аниме сезон, возможно null
     * rating - возрастной рейтинг, возможно null
     * genre - жанры, возможно null
     * myList - статус аниме в моем списке, возможно null
     * search - поиск по названию, строка, возможно null
     * @return - список полученных аниме
     */
    fun getAnimesList(page: Int, order: String?, kind: String?, status: String?,
                      season: String?, rating: String?, genre: String?, myList: String?,
                      search: String?) {

        progressBar?.visibility = View.VISIBLE

        KoshikiApplication.shikiApi.getAnimesList(page, order, kind, status,
                season, rating, genre, myList, search)
                .enqueue(object: Callback<List<AnimeListPojo>> {

                    override fun onResponse(call: Call<List<AnimeListPojo>>, response: Response<List<AnimeListPojo>>) {
                        if (response.body().isNotEmpty())
                            listener.onLoadSuccess(call, response)
                        else
                            onFailure(call, Throwable("List size is null"))

                        progressBar?.visibility = View.GONE
                    }

                    override fun onFailure(call: Call<List<AnimeListPojo>>?, t: Throwable) {
                        listener.onLoadFailure(t)

                        progressBar?.visibility = View.GONE
                    }
                })
    }

    /**
     * GET запрос /api/animes/{id}
     * animeId - id аниме
     */
    fun getAnimeById(animeId: Int) {

    }

    /**
     * GET запрос /api/animes/{id}/roles
     * animeId - id аниме
     * roles - строка, "all" - функция возвращает всех персонажей, "main" - функция возвращает главных героев
     */
    fun getAnimeRoles(animeId: Int, roles: String) {

    }

    /**
     * GET запрос /api/animes/{id}/similar (список похожих аниме)
     * animeId - id аниме
     */
    fun getAnimeSimilar(animeId: Int) {

    }

    /**
     * GET запрос /api/animes/{id}/related (список связанных аниме)
     * animeId - id аниме
     */
    fun getAnimeRelated(animeId: Int) {

    }

    /**
     * GET запрос /api/animes/{id}/screenshots
     * animeId - id аниме
     */
    fun getAnimeScreenshots(animeId: Int) {

    }
}