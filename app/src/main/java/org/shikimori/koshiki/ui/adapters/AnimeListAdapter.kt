package org.shikimori.koshiki.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.shikimori.koshiki.R
import org.shikimori.koshiki.data.network.models.pojo.AnimeListPojo
import org.shikimori.koshiki.ui.adapters.AnimeListAdapter.AnimeViewHolder
import org.shikimori.koshiki.ui.adapters.interfaces.IRecyclerAdapter
import org.shikimori.koshiki.ui.adapters.interfaces.OnEndListListener
import org.shikimori.koshiki.ui.customviews.AnimeListCardview

/**
 * Created by Александр on 18.04.2017.
 */
class AnimeListAdapter(val mContext: Context) : RecyclerView.Adapter<AnimeViewHolder>(), IRecyclerAdapter {

    // список аниме
    private val mList by lazy { ArrayList<AnimeListPojo>() }
    // слушатель конца списка
    private var mEndListListener: OnEndListListener? = null
    // переменная == true, если конец списка
    private var mEndListChecker: Boolean = false

    /**
     * Метод переопределяет слушатель конца списка
     */
    fun setOnEndListListener(listener: OnEndListListener) {
        mEndListListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_animelist_item, parent, false)
        return AnimeViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.card.initCard(mList[position])

        // TODO 18.04.2017 добавить подгрузку глайдом holder.card.animeImage

        // TODO 18.04.2017 переопределить слушатель нажатия на карточку
        holder.card.setOnCardClickListener(object: View.OnClickListener {

            override fun onClick(p0: View) {
                Toast.makeText(mContext, "Card click", Toast.LENGTH_SHORT).show()
            }
        })

        // проверяем на конце списка recyclerView
        if (mEndListChecker && (position == getItemCount() - 1)) {
            mEndListListener?.onEndList()
            mEndListChecker = false
        }
    }

    /**
     * Метод возвращает элементы списка
     */
    fun getItems() = mList

    /**
     * Метод возвращает количество элементов в списке
     */
    override fun getItemCount() = mList.size

    override fun setItems(items: MutableList<*>) {
        // приводим items к ArrayList
        mList.clear()
        mList.addAll(items as ArrayList<AnimeListPojo>)
        mEndListChecker = true
    }

    override fun addItems(items: MutableList<*>) {
        mList.addAll(mList.size, items as ArrayList<AnimeListPojo>)
        if (mList.size != 0)
            mEndListChecker = true
    }

    class AnimeViewHolder(context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = AnimeListCardview(context, itemView)
    }
}