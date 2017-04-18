package org.shikimori.koshiki.ui.activities

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

import org.shikimori.koshiki.R
import org.shikimori.koshiki.R.id.drawerLayout

/**
 * Created by alex on 18.04.17.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Views:
     * vToolbar -> Toolbar
     * vDrawerLayout -> Drawer Layout
     * vNavigationView -> Navigation View
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initControlViews()
    }

    /**
     * Инициализация управляющих элементов
     */
    private fun initControlViews() {
        // устанавливает toolbar как actionbar
        setSupportActionBar(vToolbar)

        val toggle = ActionBarDrawerToggle(this, vDraverLayout, vToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        vDraverLayout.addDrawerListener(toggle)
        toggle.syncState()

        // слушатель кликов меню
        vNavigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                // todo 18.04.2017 обработчик нажатия на элементы меню

                else -> {
                    // иначе просто закрывает navigationView
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }
}