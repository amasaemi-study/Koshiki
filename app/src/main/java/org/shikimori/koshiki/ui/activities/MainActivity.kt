package org.shikimori.koshiki.ui.activities

import android.app.FragmentTransaction
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar

import org.shikimori.koshiki.R
import org.shikimori.koshiki.ui.fragments.AnimeListFragment
import org.shikimori.koshiki.ui.fragments.BaseFragment

/**
 * Created by alex on 18.04.17.
 */
class MainActivity : AppCompatActivity() {

    // views
    var vDrawerLayout: DrawerLayout? = null
    var vToolbar: Toolbar? = null
    var vProgressBar: ProgressBar? = null
    // val vFragmentContainer = findViewById(R.id.activity_main_fragment_container) as FrameLayout
    var vNavigationView: NavigationView? = null

    // tags
    val TAG = "MainActivity"

    var FRAGMENT_TAG: String? = null

    // fragment managers
    val mFragmentManager = fragmentManager
    var mFragmentTransaction: FragmentTransaction? = null
    var mBaseFragment: BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initControlViews()

        addFragment(AnimeListFragment.TAG, AnimeListFragment)
    }

    /**
     * Инициализация управляющих элементов
     */
    private fun initControlViews() {
        vDrawerLayout = findViewById(R.id.activity_main_drawer_layout) as DrawerLayout
        vToolbar = findViewById(R.id.activity_main_toolbar) as Toolbar
        vProgressBar = findViewById(R.id.activity_main_progress_bar) as ProgressBar
        vNavigationView = findViewById(R.id.activity_main_navigation_view) as NavigationView

        // устанавливает toolbar как actionbar
        setSupportActionBar(vToolbar)

        val toggle = ActionBarDrawerToggle(this, vDrawerLayout, vToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        vDrawerLayout!!.addDrawerListener(toggle)
        toggle.syncState()

        // слушатель кликов меню
        vNavigationView!!.setNavigationItemSelectedListener {
            vDrawerLayout!!.closeDrawers()
            when(it.itemId) {
                // todo 18.04.2017 обработчик нажатия на элементы меню
                R.id.nav_view_anime_catalog -> {
                    addFragment(AnimeListFragment.TAG, AnimeListFragment)
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
        if (mBaseFragment!!.onBackPressed())
            super.onBackPressed()
    }
}