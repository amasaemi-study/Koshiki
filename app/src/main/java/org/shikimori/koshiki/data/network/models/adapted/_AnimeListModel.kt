package org.shikimori.koshiki.data.network.models.adapted

import android.util.Log

/**
 * Created by Александр on 18.04.2017.
 */
data class _AnimeListModel(val animeId: Int,
                           val animeImageUrl: String,
                           val animeNameRu: String,
                           val animeNameEn: String,
                           val animeKind: String,
                           val animeSeason: String,
                           val animeEpisodes: String ) {

    private val TAG = "_AnimeListModel"

    init {
        // TODO 18.04.2017 добавить valueParser

        logModel()
    }

    /**
     * Метод логгирует модель
     */
    private fun logModel() {
        Log.i(TAG, "--- Build model ---")
        Log.i(TAG, "id: ${this.animeId}")
        Log.i(TAG, "image: ${this.animeImageUrl}")
        Log.i(TAG, "ru: ${this.animeNameRu}")
        Log.i(TAG, "jp: ${this.animeNameEn}")
        Log.i(TAG, "kind: ${this.animeKind}")
        Log.i(TAG, "episodes: ${this.animeEpisodes}")
        Log.i(TAG, "season: ${this.animeSeason}")
        Log.i(TAG, "--- End model ---")
    }
}