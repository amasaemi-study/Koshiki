package org.shikimori.koshiki.ui.customviews

import android.content.Context
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.shikimori.koshiki.R

import org.shikimori.koshiki.utils.ConstantManager

import java.net.URL

/**
 * Created by alex on 18.04.17.
 */

class AnimeListCardview(mContext: Context) {
    private val TAG = "AnimeListCardview"

    // id аниме. По умолчанию - 0, в этом случае сервер вернет 404
    var id: Int = 0

    // TODO 18.04.2017 проверить работоспособность = View(context). Если нет, переписать на findById
    // сама карточка. Для нее обрабатывать onClick
    val animeCardview = CardView(mContext)

    // основной layout. Скрывать до загрузки данных
    // unactual
    // val visibilityLayout = LinearLayout(mContext)

    // постер
    val animePoster = ImageView(mContext)

    // название аниме на русском. Если название на русском отсутствует - писать на англ
    val animeNameRu = TextView(mContext)

    // название аниме на английском (транслитом)
    val animeNameEn = TextView(mContext)

    // тип аниме (tv, ova, etc)
    val animeKind = TextView(mContext)

    // аниме сезон (Зима 2015 и тд)
    val animeSeason = TextView(mContext)

    // количество эпизодов (если онгоинг -> вышло серий / всего серий; иначе вышло серий)
    val animeEpisodes = TextView(mContext)

    // аниме статус (ongoing, anons, released)
    val animeStatus = TextView(mContext)

    // меню для добавления аниме в избранное и назначения статуса просмотренного
    val cardviewMenu = ImageView(mContext)

    init {
        setCardviewMenu()
        setPosterLayoutParams(mContext)
    }

    /**
     * Метод инициализирует карточку
     */
    public fun initCard(id: Int,
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
    }

    /**
     * Метод настраивает обработчик нажатий на карточку
     */
    public fun setOnCardClickListener(listener: View.OnClickListener) {
        animeCardview.setOnClickListener(listener)
    }

    /**
     * Метод инициализирует animeNameRu и animeNameEn -> если rusName == null -> animeNameRu.text = enName
     */
    private fun initRuName(ruName: String, enName: String) {
        if(ruName.equals(ConstantManager.EMPTY_VALUE))
            animeNameRu.setText(enName)
        else {
            animeNameRu.setText(ruName)
            animeNameEn.setText(enName)
        }
    }

    /**
     * Метод инициализирует и раскрашивает view и текст статуса
     */
    private fun initStatus(status: String) {
        when(status) {
            "Анонс" -> {
                animeStatus.text = status
                animeStatus.setBackgroundColor(R.color.orange_200)
                animeStatus.setTextColor(R.color.deep_orange_900)
            }

            "Онгоинг" -> {
                animeStatus.text = status
                animeStatus.setBackgroundColor(R.color.light_green_200)
                animeStatus.setTextColor(R.color.green_900)
            }

            "Вышло" -> {
                animeStatus.text = status
                animeStatus.setBackgroundColor(R.color.light_blue_100)
                animeStatus.setTextColor(R.color.cyan_900)
            }

            else -> {
                animeStatus.text = status
                animeStatus.setBackgroundColor(R.color.brown_100)
                animeStatus.setTextColor(R.color.grey_900)
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
        animePoster.layoutParams.width = ((180 * context.resources.displayMetrics.density / 1.4).toInt())
    }
}