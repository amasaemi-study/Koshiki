package org.shikimori.koshiki.data.network.models.pojo

import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO для /api/animes
 */

data class AnimeListPojo(
        // id аниме на shikimori
        // actual
        @SerializedName("id")
        @Expose
        private val id: Int,

        // название аниме транслитом с японского на английский
        // actual
        @SerializedName("name")
        @Expose
        private var name: String,

        // название аниме на русском
        // actual
        @SerializedName("russian")
        @Expose
        private var russian: String,

        // объект с изображениями разного разрешения
        // actual
        @SerializedName("image")
        @Expose
        private var image: Image?,

        // url на shikimori
        // 16.04.17 unactual
        @SerializedName("url")
        @Expose
        private var url: String,

        // тип аниме (tv, ova, ..)
        // actual
        @SerializedName("kind")
        @Expose
        private var kind: String,

        // статус (релиз, онгоинг, ...)
        // actual
        @SerializedName("status")
        @Expose
        private var status: String,

        // эпизодов всего
        // actual
        @SerializedName("episodes")
        @Expose
        private var episodes: Int = 0,

        // вышло эпизодов (если онгоинг)
        // actual
        @SerializedName("episodes_aired")
        @Expose
        private var episodes_aired: Int = 0,

        // начало показа (сезон)
        // actual
        @SerializedName("aired_on")
        @Expose
        private var aired_on: String,

        // релиз
        // 16.04.2017 unactual
        @SerializedName("released_on")
        @Expose
        private var released_on: String
) {

    class Image {
        @SerializedName("original")
        @Expose
        var original: String = "-"

        @SerializedName("preview")
        @Expose
        var preview: String = "-"

        @SerializedName("x96")
        @Expose
        var x96: String = "-"

        @SerializedName("x48")
        @Expose
        var x48: String = "-"
    }

    private val TAG = "AnimeListPojo"

    init {
        logModel()
    }

    fun getId(): Int {
        return id
    }

    fun getEnName(): String {
        return name
    }

    fun getRuName(): String {
        return russian
    }

    fun getOriginalPoster(): String {
        return image!!.original
    }

    fun getPreviewPoster(): String {
        return image!!.preview
    }

    fun getKind(): String {
        return kind
    }

    fun getStatus(): String {
        return status
    }

    fun getEpisodes(): String {
        val episodesReturned = if (episodes == 0) "?" else episodes.toString()

        if (status == "ongoing")
            return "${episodes_aired} / ${episodesReturned}"
        return episodesReturned;
    }

    fun getAiredOn(): String {
        return aired_on
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