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

    val TAG = "AnimeListFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_animelist, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // настраиваем title у toolbar
        activity.title = getString(R.string.animelist_nav_menu_anime_catalog)

        initFindBar(view!!)
    }

    /**
     * Метод инициализирует плашку с настройками поиска
     */
    private fun initFindBar(rootView: View) {
        // todo
        SearchingBar(activity, rootView)
    }

    override fun onBackPressed(): Boolean {
        return super.onBackPressed()
    }
}