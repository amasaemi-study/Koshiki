package org.shikimori.koshiki.ui.adapters

import org.shikimori.koshiki.data.network.models.adapted._AnimeListModel

/**
 * Created by Александр on 18.04.2017.
 */
interface IRecyclerAdapter {
    // TODO 18.04.2017 пока в методах используется _AnimeListModel как генерик. Подумать, как привести к общейму виду

    /**
     * Метод задает список элементов адаптера
     */
    fun setItems(items: MutableList<*>)

    /**
     * Метод добавляет к текущим элементам cписка адаптера новые
     */
    fun addItems(items: MutableList<*>)
}