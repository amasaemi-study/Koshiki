package org.shikimori.koshiki.ui.activities

import android.app.FragmentTransaction
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.widget.ProgressBar

import org.shikimori.koshiki.R
import org.shikimori.koshiki.data.network.managers.OnDownloadFinish
import org.shikimori.koshiki.data.network.managers.AnimesManager
import org.shikimori.koshiki.ui.fragments.AnimeListFragment
import org.shikimori.koshiki.ui.fragments.BaseFragment
import retrofit2.Call
import retrofit2.Response

/**
 * Created by alex on 18.04.17.
 */
class MainActivity : AppCompatActivity() {

    // views
    private val vDrawerLayout by lazy { findViewById(R.id.activity_main_drawer_layout) as DrawerLayout }
    private val vToolbar by lazy { findViewById(R.id.activity_main_toolbar) as Toolbar }
    private val vNavigationView by lazy { findViewById(R.id.activity_main_navigation_view) as NavigationView }

    // tags
    private val TAG = "MainActivity"

    private var FRAGMENT_TAG: String? = null

    // fragment managers
    private val mFragmentManager = fragmentManager
    private var mFragmentTransaction: FragmentTransaction? = null
    private var mBaseFragment: BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initControlViews()

        addFragment(AnimeListFragment.TAG, AnimeListFragment.newInstance())
    }

    /**
     * Инициализация управляющих элементов
     */
    private fun initControlViews() {

        // устанавливает toolbar как actionbar
        setSupportActionBar(vToolbar)

        val toggle = ActionBarDrawerToggle(this, vDrawerLayout, vToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        vDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // слушатель кликов меню
        vNavigationView.setNavigationItemSelectedListener {
            vDrawerLayout.closeDrawers()
            when(it.itemId) {
                // todo 18.04.2017 обработчик нажатия на элементы меню
                R.id.nav_view_anime_catalog -> {
                    addFragment(AnimeListFragment.TAG, AnimeListFragment.newInstance())
                    return@setNavigationItemSelectedListener true
                }

                else -> {
                    // иначе просто закрывает navigationView
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }

    /**
     * Метод добавляет фрагмент на экран
     */
    private fun addFragment(tag: String, baseFragment: BaseFragment) {
        if (!FRAGMENT_TAG.equals(tag)) {
            FRAGMENT_TAG = tag
            mBaseFragment = baseFragment

            mFragmentTransaction = mFragmentManager.beginTransaction()
            mFragmentTransaction?.replace(R.id.activity_main_fragment_container, mBaseFragment, FRAGMENT_TAG)?.commit()
        }
    }

    override fun onBackPressed() {
        if (vDrawerLayout.isDrawerOpen(Gravity.START))
            vDrawerLayout.closeDrawers()
        else if (mBaseFragment!!.onBackPressed())
            super.onBackPressed()
    }
}