package org.shikimori.koshiki.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.shikimori.koshiki.R
import org.shikimori.koshiki.data.network.models.adapted.AnimeListModel
import org.shikimori.koshiki.ui.adapters.AnimeListAdapter.AnimeViewHolder
import org.shikimori.koshiki.ui.adapters.interfaces.OnEndListListener
import org.shikimori.koshiki.ui.customviews.AnimeListCardview

/**
 * Created by Александр on 18.04.2017.
 */
class AnimeListAdapter(context: Context) : RecyclerView.Adapter<AnimeViewHolder>(), IRecyclerAdapter {

    // список аниме
    val mList: MutableList<AnimeListModel>? = null
    // слушатель конца списка
    var mEndListListener: OnEndListListener? = null
    // переменная == true, если конец списка
    var mEndListChecker: Boolean = false

    /**
     * Метод переопределяет слушатель конца списка
     */
    public fun setOnEndListListener(listener: OnEndListListener) {
        mEndListListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.cardview_animelist_item, parent, false)
        return AnimeViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder?, position: Int) {
        holder!!.card.animeNameRu.text = mList!!.get(position).animeNameRu
        holder.card.animeNameEn.text = mList.get(position).animeNameEn
        holder.card.animeKind.text = mList.get(position).animeKind
        holder.card.animeSeason.text = mList.get(position).animeSeason
        holder.card.animeEpisodes.text = mList.get(position).animeEpisodes

        // TODO 18.04.2017 добавить подгрузку глайдом holder.card.animeImage

        // TODO 18.04.2017 переопределить слушатель нажатия на карточку
        // holder.card.setOnCardClickListener(View.OnClickListener { })

        // проверяем на конце списка recyclerView
        if (mEndListChecker && (position == getItemCount() - 1)) {
            mEndListListener!!.onEndList()
            mEndListChecker = false
        }
    }

    /**
     * Метод возвращает элементы списка
     */
    fun getItems(): MutableList<AnimeListModel> {
        return mList!!
    }

    /**
     * Метод возвращает количество элементов в списке
     */
    override fun getItemCount(): Int {
        return if (mList != null) mList.size else 0
    }

    override fun setItems(items: MutableList<*>) {
        // приводим items к mutableListModel
        mList!!.addAll(items as MutableList<AnimeListModel>)
        mEndListChecker = true
    }

    override fun addItems(items: MutableList<*>) {
        mList!!.addAll(mList.size, items as MutableList<AnimeListModel>)
        if (mList.size != 0)
            mEndListChecker = true
    }

    class AnimeViewHolder(context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = AnimeListCardview(context)
    }
}