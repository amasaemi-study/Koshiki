package org.shikimori.koshiki.ui.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import org.shikimori.koshiki.R
import org.shikimori.koshiki.data.network.managers.OnDownloadFinish
import org.shikimori.koshiki.data.network.models.pojo.AnimeListPojo
import org.shikimori.koshiki.managers.AnimesManager
import org.shikimori.koshiki.ui.adapters.AnimeListAdapter
import org.shikimori.koshiki.ui.adapters.interfaces.OnEndListListener
import org.shikimori.koshiki.ui.customviews.SearchingBar
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Александр on 18.04.2017.
 */
object AnimeListFragment : BaseFragment() {

    val TAG = "AnimeListFragment"

//    private val vProgressBar by lazy { activity.findViewById(R.id.activity_main_progress_bar) as ProgressBar }
    lateinit private var mSearchingBar: SearchingBar
    lateinit private var mRecyclerView: RecyclerView

    // list adapter
    private val mListAdapter by lazy { AnimeListAdapter(activity) }

    // API manager
    private val mAnimesManager = AnimesManager(/*vProgressBar*/ null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_animelist, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // настраиваем title у toolbar
        activity.title = getString(R.string.animelist_nav_menu_anime_catalog)

        initFindBar(view)
        initList(view)

        getAnimeList(1, 15, null, null, null, null, null, null, null, null)
    }

    /**
     * Метод инициализирует плашку с настройками поиска
     */
    private fun initFindBar(rootView: View) {
        mSearchingBar = SearchingBar(activity, rootView)
    }

    /**
     * Метод инициализирует меню для поиска по названию
     */
    private fun initSearchBar() {

    }

    /**
     * Метод инициализирует RecyclerView, adapter и список
     */
    private fun initList(rootView: View) {
        mRecyclerView = rootView.findViewById(R.id.animelist_fragment_recycler_view) as RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mListAdapter

        // переопределяем действие по окончанию загрузки
        mAnimesManager.setOnDownloadFinishListener(object: OnDownloadFinish {
            override fun onLoadSuccess(call: Call<*>, response: Response<*>) {
                // пока такой костыльный метод проверки, является ли запрос началом нового списка
                if (call.request().url().toString().contains("page=1&")) {
                    mListAdapter.setItems(response.body() as MutableList<*>)
                    mRecyclerView.scrollToPosition(0)
                } else {
                    mListAdapter.addItems(response.body() as MutableList<*>)
                }

                // обновляем адаптер с позиции добавленных элементов в список
                mListAdapter.notifyItemRangeInserted(mListAdapter.getItemCount() - 15, mListAdapter.getItemCount());
            }

            override fun onLoadFailure(throwable: Throwable) {
                // TODO
                throwable.stackTrace
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        mListAdapter.setOnEndListListener(object: OnEndListListener {
            override fun onEndList() {
                // TODO обработать конец списка
            }
        })
    }

    /**
     * Метод получает список от менеджера и обновляет адаптер
     */
    private fun getAnimeList(page: Int, limit: Int, order: String?, kind: String?, status: String?,
                             season: String?, rating: String?, genre: String?, myList: String?,
                             search: String?) {

        mAnimesManager.getAnimesList(page, limit, order, kind, status, season, rating, genre,
                myList, search
        )
    }

    override fun onBackPressed(): Boolean {
        if (mSearchingBar.isVisible()) {
            mSearchingBar.changeFindBarVisibilityState()
            return false
        }

        return true
    }
}