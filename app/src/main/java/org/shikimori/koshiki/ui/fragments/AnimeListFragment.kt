package org.shikimori.koshiki.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import org.shikimori.koshiki.R
import org.shikimori.koshiki.ui.customviews.SearchingBar

/**
 * Created by Александр on 18.04.2017.
 */
object AnimeListFragment : BaseFragment() {

    // tags
    val TAG = "AnimeListFragment"

    var mSearchingBar: SearchingBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_animelist, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // настраиваем title у toolbar
        activity.title = getString(R.string.animelist_nav_menu_anime_catalog)

        initFindBar(view)
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

    override fun onBackPressed(): Boolean {
        if (mSearchingBar!!.isVisible()) {
            mSearchingBar!!.changeFindBarVisibilityState()
            return false
        }

        return true
    }
}