package org.shikimori.koshiki.data.network.models.pojo

import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.shikimori.koshiki.utils.ValueParser

/**
 * POJO для /api/animes
 */

open class AnimeListPojo {

    private val TAG = "AnimeListPojo"
    private val parser: ValueParser

    init {
        logModel()

        parser = ValueParser()
    }

    // id аниме на shikimori
    // actual
    @SerializedName("id")
    @Expose
    private var id: Int = 0

    fun getId(): Int {
        return id
    }

    // название аниме транслитом с японского на английский
    // actual
    @SerializedName("name")
    @Expose
    private var name: String? = null

    fun getEnName(): String {
        return notNull(name)
    }

    // название аниме на русском
    // actual
    @SerializedName("russian")
    @Expose
    private var russian: String? = null

    fun getRuName(): String {
        return notNull(russian)
    }

    // объект с изображениями разного разрешения
    // actual
    @SerializedName("image")
    @Expose
    private var image: Image? = null

    fun getOriginalPoster(): String {
        return notNull(image!!.original)
    }

    fun getPreviewPoster(): String {
        return notNull(image!!.preview)
    }

    // url на shikimori
    // 16.04.17 unactual
    @SerializedName("url")
    @Expose
    private var url: String? = null

    // тип аниме (tv, ova, ..)
    // actual
    @SerializedName("kind")
    @Expose
    private var kind: String? = null

    fun getKind(): Int {
        //return notNull(kind)
        return parser.getKindInt(kind)
    }

    // статус (релиз, онгоинг, ...)
    // actual
    @SerializedName("status")
    @Expose
    private var status: String? = null

    fun getStatus(): Int {
        //return notNull(status)
        return parser.getStatusInt(status)
    }

    // эпизодов всего
    // actual
    @SerializedName("episodes")
    @Expose
    private var episodes: Int = 0

    fun getEpisodes(): String {
        val episodesReturned: String = if (episodes == 0) "?" else episodes.toString()

        if (status.equals("ongoing"))
            return "${episodes_aired} / ${episodesReturned}"
        return episodesReturned;
    }

    // вышло эпизодов (если онгоинг)
    // actual
    @SerializedName("episodes_aired")
    @Expose
    private var episodes_aired: Int = 0

    // начало показа (сезон)
    // actual
    @SerializedName("aired_on")
    @Expose
    private var aired_on: String? = null

    fun getAiredOn(): String {
        return notNull(aired_on)
    }

    // релиз
    // 16.04.2017 unactual
    @SerializedName("released_on")
    @Expose
    var released_on: String? = null

    class Image {
        @SerializedName("original")
        @Expose
        var original: String? = null

        @SerializedName("preview")
        @Expose
        var preview: String? = null

        @SerializedName("x96")
        @Expose
        var x96: String? = null

        @SerializedName("x48")
        @Expose
        var x48: String? = null
    }

    /**
     * Проверяет строку на null
     */
    private fun notNull(string: String?): String {
        if (string != null)
            return string
        else
            return "-"
    }

    /**
     * Метод логгирует модель
     */
    private fun logModel() {
        Log.i(TAG, "--- Build model ---")
        Log.i(TAG, "id: ${this.getId()}")
        Log.i(TAG, "image: ${this.getOriginalPoster()}")
        Log.i(TAG, "ru: ${this.getRuName()}")
        Log.i(TAG, "jp: ${this.getEnName()}")
        Log.i(TAG, "kind: ${this.getKind()}")
        Log.i(TAG, "status: ${this.getStatus()}")
        Log.i(TAG, "episodes: ${this.getEpisodes()}")
        Log.i(TAG, "season: ${this.getAiredOn()}")
        Log.i(TAG, "--- End model ---")
    }
}