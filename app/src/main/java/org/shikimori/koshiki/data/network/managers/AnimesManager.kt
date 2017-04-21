package org.shikimori.koshiki.managers

import android.content.Context
import org.shikimori.koshiki.data.network.models.pojo.AnimeListPojo

/**
 * Менеджер для работы с Animes API
 */

class AnimesManager(val mContext: Context) {

    /**
     * GET запрос /api/animes
     * page - страница, целое число
     * limit - количество элементов на странице, целое число (по умолчанию - 15)
     * order - сортировка, целое число, трансформируется в строку парсером, возможно null
     * kind - тип аниме, массив целых чисел, трансформируется в строку парсером, возможно null
     * status - статус аниме, массив целых чисел, трансформируется в строку парсером, возможно null
     * season - аниме сезон, массив целых чисел, трансформируется в строку парсером, возможно null
     * rating - возрастной рейтинг, массив целых чисел, трансформируется в строку парсером, возможно null
     * genre - жанры, массив целых чисел, трансформируется в строку парсером, возможно null
     * myList - статус аниме в моем списке, массив целых чисел, трансформируется в строку парсером, возможно null
     * search - поиск по названию, строка, возможно null
     * @return - список полученных аниме
     */
    fun getAnimesList(page: Int, limit: Int, order: Int?, kind: IntArray?, status: IntArray?,
                      season: IntArray?, rating: IntArray?, genre: IntArray?, myList: IntArray?,
                      search: String?): List<AnimeListPojo> {
        return null
    }

    /**
     * GET запрос /api/animes/{id}
     * animeId - id аниме, целое число
     * @return - объект описания аниме
     */
    fun getAnimeById(animeId: Int): String /* Вместо стринга - объект аниме */ {
        return "abc"
    }

    /**
     * GET запрос /api/animes/{id}/roles
     * animeId - id аниме, целое число
     * roles - строка, "all" - функция возвращает всех персонажей, "main" - функция возвращает главных героев
     * @return - коллекция персонажей, может быть null
     */
    fun getAnimeRoles(animeId: Int, roles: String): String? /* Вместо стринга - список из персонажей */ {
        return null
    }

    /**
     * GET запрос /api/animes/{id}/similar (список похожих аниме)
     * animeId - id аниме, целое число
     * @return - коллекция аниме, может быть null
     */
    fun getAnimeSimilar(animeId: Int): String?/* Вместо стринга - список аниме */ {
        return null
    }

    /**
     * GET запрос /api/animes/{id}/related (список связанных аниме)
     * animeId - id аниме
     * @return - коллекция аниме, может быть null
     */
    fun getAnimeRelated(animeId: Int): String? /* Вместо стринга - список аниме */ {
        return null
    }

    /**
     * GET запрос /api/animes/{id}/screenshots
     * animeId - id аниме
     * @return - коллекция скриншотов, может быть null
     */
    fun getAnimeScreenshots(animeId: Int): String? /* Вместо стринга - список картинок */ {
        return null
    }
}