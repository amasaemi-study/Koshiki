package org.shikimori.koshiki.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.shikimori.koshiki.R

/**
 * Created by Александр on 18.04.2017.
 */
object AnimeListFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // TODO 18.04.2017 проверить работоспособность inflater!!
        return inflater!!.inflate(R.layout.fragment_animelist, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        // настраиваем title у toolbar
        activity.title = getString(R.string.animelist_nav_menu_anime_catalog)

        initFindBar()
    }

    /**
     * Метод инициализирует плашку с настройками поиска
     */
    private fun initFindBar() {
        // todo
    }

    override fun onBackPressed(): Boolean {
        return super.onBackPressed()
    }
}