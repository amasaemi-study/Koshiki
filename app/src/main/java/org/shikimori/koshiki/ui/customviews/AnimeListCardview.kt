package org.shikimori.koshiki.ui.customviews

import android.content.Context
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.shikimori.koshiki.R

import org.shikimori.koshiki.utils.ConstantManager
import org.shikimori.koshiki.utils.ValueParser

import java.net.URL

/**
 * Created by alex on 18.04.17.
 */

class AnimeListCardview(mContext: Context) {
    private val TAG = "AnimeListCardview"
    private val parser: ValueParser

    // id аниме. По умолчанию - 0, в этом случае сервер вернет 404
    private var id: Int = 0

    // TODO 18.04.2017 проверить работоспособность = View(context). Если нет, переписать на findById
    // сама карточка. Для нее обрабатывать onClick
    private val animeCardview = CardView(mContext)

    // основной layout. Скрывать до загрузки данных
    // unactual
    // val visibilityLayout = LinearLayout(mContext)

    // постер
    private val animePoster = ImageView(mContext)

    // название аниме на русском. Если название на русском отсутствует - писать на англ
    private val animeNameRu = TextView(mContext)

    // название аниме на английском (транслитом)
    private val animeNameEn = TextView(mContext)

    // тип аниме (tv, ova, etc)
    private val animeKind = TextView(mContext)

    // аниме сезон (Зима 2015 и тд)
    private val animeSeason = TextView(mContext)

    // количество эпизодов (если онгоинг -> вышло серий / всего серий; иначе вышло серий)
    private  val animeEpisodes = TextView(mContext)

    // аниме статус (ongoing, anons, released)
    private val animeStatus = TextView(mContext)

    // меню для добавления аниме в избранное и назначения статуса просмотренного
    private val cardviewMenu = ImageView(mContext)

    init {
        setCardviewMenu()
        setPosterLayoutParams(mContext)

        parser = ValueParser()
    }

    /**
     * Метод инициализирует карточку
     */
    /*fun initCard(id: Int,
                        posterUrl: String,
                        animeNameRu: String,
                        animeNameEn: String,
                        animeKind: String,
                        animeSeason: String,
                        animeEpisodes: String,
                        animeStatus: String) {
        this.id = id
            initRuName(animeNameRu, animeNameEn)
        // this.animeNameRu.setText(animeNameRu)
        // this.animeNameEn.setText(animeNameEn)
        this.animeKind.setText(animeKind)
        this.animeSeason.setText(animeSeason)
        this.animeEpisodes.setText(animeEpisodes)
            initStatus(animeStatus)
        // this.animeStatus.setText(animeStatus)
        // TODO 18.04.2017 подгрузить глайдом изображение в animePoster. Линк - posterUrl
        // TODO 18.04.2017 при неудачной загрузке изображения ставить дефолтную картинку
    }*/

    /**
     * Метод настраивает обработчик нажатий на карточку
     */
    fun setOnCardClickListener(listener: View.OnClickListener) {
        animeCardview.setOnClickListener(listener)
    }

    /**
     * Метод устанавливает картинку у постера
     */
    fun setPoster(posterUrl: String) {
        // TODO 18.04.2017 подгрузить глайдом изображение в animePoster. Линк - posterUrl
        // TODO 18.04.2017 при неудачной загрузке изображения ставить дефолтную картинку
    }

    /**
     * Метод устанавливает id
     */
    fun setId(id: Int) {
        this.id = id
    }

    /**
     * Метод устанавливает русское и английское назания для аниме
     */
    fun setTitles(ruName: String, enName: String) {
        initRuName(ruName, enName)
    }

    /**
     * Метод устанавливает тип аниме
     */
    fun setKind(kind: Int) {
        this.animeKind.text = parser.getKind(true, kind)
    }

    /**
     * Метод устанавливает сезон выхода аниме
     */
    fun setSeason(season: String) {
        this.animeSeason.text = parser.getSeason(season)
    }

    /**
     * Метод устанавливает количество эпизодов
     */
    fun setEpisodes(episodes: String) {
        this.animeEpisodes.text = episodes
    }

    fun setStatus(status: Int) {
        initStatus(status)
    }

    /**
     * Метод инициализирует animeNameRu и animeNameEn -> если rusName == null -> animeNameRu.text = enName
     */
    private fun initRuName(ruName: String, enName: String) {
        if(ruName.equals(ConstantManager.EMPTY_VALUE))
            this.animeNameRu.setText(enName)
        else {
            this.animeNameRu.setText(ruName)
            this.animeNameEn.setText(enName)
        }
    }

    /**
     * Метод инициализирует и раскрашивает view и текст статуса
     */
    private fun initStatus(status: Int) {
        when(status) {
            0 -> {
                this.animeStatus.text = parser.getStatus(true, status)
                this.animeStatus.setBackgroundColor(R.color.orange_200)
                this.animeStatus.setTextColor(R.color.deep_orange_900)
            }

            1 -> {
                this.animeStatus.text = parser.getStatus(true, status)
                this.animeStatus.setBackgroundColor(R.color.light_green_200)
                this.animeStatus.setTextColor(R.color.green_900)
            }

            2 -> {
                this.animeStatus.text = parser.getStatus(true, status)
                this.animeStatus.setBackgroundColor(R.color.light_blue_100)
                this.animeStatus.setTextColor(R.color.cyan_900)
            }

            else -> {
                this.animeStatus.text = parser.getStatus(true, status)
                this.animeStatus.setBackgroundColor(R.color.brown_100)
                this.animeStatus.setTextColor(R.color.grey_900)
            }
        }
    }

    /**
     * Метод настраивает обработчки меню cardviewMenu
     */
    private fun setCardviewMenu() {
        // TODO 18.04.2017 обработчик меню cardviewMenu (popup)
    }

    /**
     * Метод настраивает ширину картинки
     */
    private fun setPosterLayoutParams(context: Context) {
        this.animePoster.layoutParams.width = ((180 * context.resources.displayMetrics.density / 1.4).toInt())
    }
}