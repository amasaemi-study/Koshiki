package org.shikimori.koshiki.ui.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*

import org.shikimori.koshiki.R
import org.shikimori.koshiki.data.network.managers.OnDownloadFinish
import org.shikimori.koshiki.data.network.managers.AnimesManager
import org.shikimori.koshiki.ui.adapters.AnimeListAdapter
import org.shikimori.koshiki.ui.adapters.interfaces.OnEndListListener
import org.shikimori.koshiki.ui.customviews.SearchingBar
import org.shikimori.koshiki.ui.customviews.swiperefresh.SwipeRefreshLayout

import retrofit2.Call
import retrofit2.Response

/**
 * Created by Александр on 18.04.2017.
 */
class AnimeListFragment : BaseFragment() {

    // newInstance pattern
    companion object {
        val TAG = "AnimeListFragment"

        fun newInstance(): AnimeListFragment {
            val fragment = AnimeListFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }

    // request params
    var page: Int = 1
    var order: String? = null
    var kind: String? = null
    var status: String? = null
    var season: String? = null
    var rating: String? = null
    var genre: String? = null
    var myList: String? = null
    var search: String? = null

    lateinit private var mSearchingBar: SearchingBar
    lateinit private var mRecyclerView: RecyclerView
    lateinit private var mEmptyLabel: View

    // list adapter
    private val mListAdapter by lazy { AnimeListAdapter(activity) }

    // API manager
    lateinit private var mAnimesManager: AnimesManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_animelist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // настраиваем title у toolbar
        activity.title = getString(R.string.animelist_nav_menu_anime_catalog)

        mEmptyLabel = view.findViewById(R.id.animelist_fragment_empty_label)

        initFindBar(view)
        initList(view)

        getAnimeList(page, order, kind, status, season, rating, genre, myList, search)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    /**
     * Метод инициализирует плашку с настройками поиска
     */
    private fun initFindBar(rootView: View) {
        mSearchingBar = SearchingBar(activity, rootView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_animelist_search, menu)

        val item = menu.findItem(R.id.menu_activity_list_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = getString(R.string.animelist_fragment_input_anime_name)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String): Boolean {
                onBackPressed()
                // выполняем поисковой запрос
                getAnimeList(1, null, null, null, null, null, null, null, p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // todo поиск в реальном времени. Скорее всего не понадобится
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Метод инициализирует RecyclerView, adapter и список
     */
    private fun initList(rootView: View) {
        mRecyclerView = rootView.findViewById(R.id.animelist_fragment_recycler_view) as RecyclerView
        //settingRecyclerViewLayoutManager(mRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mListAdapter

        mAnimesManager = AnimesManager(rootView.findViewById(R.id.animelist_fragment_refresh) as SwipeRefreshLayout)
        // переопределяем действие по окончанию загрузки
        mAnimesManager.setOnDownloadFinishListener(object: OnDownloadFinish {

            override fun onLoadSuccess(call: Call<*>, response: Response<*>) {
                if ((response.body() as MutableList<*>).size == 0)
                    mEmptyLabel.visibility = View.VISIBLE
                else
                // пока такой костыльный метод проверки, является ли запрос началом нового списка
                if (call.request().url().toString().contains("page=1&")) {
                    mEmptyLabel.visibility = View.GONE

                    mListAdapter.setItems(response.body() as MutableList<*>)
                    mRecyclerView.scrollToPosition(0)
                    mListAdapter.notifyDataSetChanged()
                } else {
                    mListAdapter.addItems(response.body() as MutableList<*>)

                    // обновляем адаптер с позиции добавленных элементов в список
                    mListAdapter.notifyItemRangeInserted(
                            mListAdapter.getItemCount() - (response.body() as MutableList<*>).size, mListAdapter.getItemCount());
                }
            }

            override fun onLoadFailure(throwable: Throwable) {
                // TODO
                mEmptyLabel.visibility = View.VISIBLE

                Log.e(TAG, throwable.message)
            }
        })

        mListAdapter.setOnEndListListener(object: OnEndListListener {
            override fun onEndList() {
                page++
                getAnimeList(page, order, kind, status, season, rating, genre, myList, search)
            }
        })
    }

    /**
     * Метод настраивает LayoutManager для RecyclerView под разные ориентации экрана
     * Добавить в будущих версиях
     */
    /*private fun settingRecyclerViewLayoutManager(recyclerView: RecyclerView) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.layoutManager = LinearLayoutManager(activity)
        else
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
    }*/

    /**
     * Метод получает список от менеджера и обновляет адаптер
     */
    private fun getAnimeList(page: Int, order: String?, kind: String?, status: String?,
                             season: String?, rating: String?, genre: String?, myList: String?,
                             search: String?) {

        this.page = page
        this.order = order
        this.kind = kind
        this.status = status
        this.season = season
        this.rating = rating
        this.genre = genre
        this.myList = myList
        this.search = search

        mAnimesManager.getAnimesList(page, order, kind, status, season, rating, genre,
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