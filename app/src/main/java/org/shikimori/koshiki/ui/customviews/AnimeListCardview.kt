package org.shikimori.koshiki.ui.customviews

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.shikimori.koshiki.R
import org.shikimori.koshiki.data.network.models.pojo.AnimeListPojo

import org.shikimori.koshiki.utils.ConstantManager
import org.shikimori.koshiki.utils.ValueParser

import java.net.URL

/**
 * Created by alex on 18.04.17.
 */

class AnimeListCardview(val mContext: Context, rootView: View) {
    private val TAG = "AnimeListCardview"
    private val parser: ValueParser

    // id аниме. По умолчанию - 0, в этом случае сервер вернет 404
    private var id: Int = 0

    // TODO 18.04.2017 проверить работоспособность = View(context). Если нет, переписать на findById
    // сама карточка. Для нее обрабатывать onClick
    private val animeCardview by lazy { rootView.findViewById(R.id.animelist_cardview_cardview) }

    // основной layout. Скрывать до загрузки данных
    // unactual
    // val visibilityLayout = LinearLayout(mContext)

    // постер
    private val animePoster by lazy { rootView.findViewById(R.id.animelist_cardview_poster) as ImageView }

    // название аниме на русском. Если название на русском отсутствует - писать на англ
    private val animeNameRu by lazy { rootView.findViewById(R.id.animelist_cardview_name_ru) as TextView }

    // название аниме на английском (транслитом)
    private val animeNameEn by lazy { rootView.findViewById(R.id.animelist_cardview_name_en) as TextView }

    // тип аниме (tv, ova, etc)
    private val animeKind by lazy { rootView.findViewById(R.id.animelist_cardview_kind) as TextView }

    // аниме сезон (Зима 2015 и тд)
    private val animeSeason by lazy { rootView.findViewById(R.id.animelist_cardview_season) as TextView }

    // количество эпизодов (если онгоинг -> вышло серий / всего серий; иначе вышло серий)
    private  val animeEpisodes by lazy { rootView.findViewById(R.id.animelist_cardview_episodes) as TextView }

    // аниме статус (ongoing, anons, released)
    private val animeStatus by lazy { rootView.findViewById(R.id.animelist_cardview_status) as TextView }

    // меню для добавления аниме в избранное и назначения статуса просмотренного
    private val cardviewMenu by lazy { rootView.findViewById(R.id.animelist_cardview_popup_menu) }

    init {
        setPosterLayoutParams(mContext)

        parser = ValueParser()
    }

    /**
     * Метод инициализирует карточку
     */
    fun initCard(model: AnimeListPojo) {
        setId(model.getId())
        setPoster(ConstantManager.SHIKI_BASE_WITHOUT_DELIMITER + model.getPreviewPoster())
        setTitles(model.getRuName(), model.getEnName())
        setKind(model.getKind())
        setSeason(model.getAiredOn())
        setEpisodes(model.getEpisodes())
        setStatus(model.getStatus())
        setCardviewMenu()
    }

    /**
     * Метод настраивает обработчик нажатий на карточку
     */
    fun setOnCardClickListener(listener: View.OnClickListener) {
        animeCardview.setOnClickListener(listener)
    }

    /**
     * Метод устанавливает картинку у постера
     */
    fun setPoster(posterUrl: String?) {
        Glide.with(mContext)
                .load(posterUrl)
                .error(R.drawable.img_load_failed)
                .dontAnimate()
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(animePoster);
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
    fun setTitles(ruName: String?, enName: String?) {
        initRuName(ruName, enName)
    }

    /**
     * Метод устанавливает тип аниме
     */
    fun setKind(kind: String?) {
        this.animeKind.text = parser.getKind(kind)
    }

    /**
     * Метод устанавливает сезон выхода аниме
     */
    fun setSeason(season: String?) {
        this.animeSeason.text = parser.getSeason(season)
    }

    /**
     * Метод устанавливает количество эпизодов
     */
    fun setEpisodes(episodes: String?) {
        this.animeEpisodes.text = episodes
    }

    fun setStatus(status: String?) {
        initStatus(status)
    }

    /**
     * Метод инициализирует animeNameRu и animeNameEn -> если rusName == null -> animeNameRu.text = enName
     */
    private fun initRuName(ruName: String?, enName: String?) {
        if(ruName == null) {
            this.animeNameRu.text = enName
            this.animeNameEn.text = ""
        } else {
            this.animeNameRu.text = ruName
            this.animeNameEn.text = enName
        }
    }

    /**
     * Метод инициализирует и раскрашивает view и текст статуса
     */
    private fun initStatus(status: String?) {
        when(status) {
            "anons" -> {
                this.animeStatus.text = parser.getStatus(status)
                this.animeStatus.setBackgroundColor(Color.parseColor("#ffecb3"))
                this.animeStatus.setTextColor(Color.parseColor("#fb8c00"))
            }

            "ongoing" -> {
                this.animeStatus.text = parser.getStatus(status)
                this.animeStatus.setBackgroundColor(Color.parseColor("#b2ebf2"))
                this.animeStatus.setTextColor(Color.parseColor("#0097a7"))
            }

            "released" -> {
                this.animeStatus.text = parser.getStatus(status)
                this.animeStatus.setBackgroundColor(Color.parseColor("#ffecb3"))
                this.animeStatus.setTextColor(Color.parseColor("#fb8c00"))
            }

            else -> {
                this.animeStatus.text = parser.getStatus(status)
                this.animeStatus.setBackgroundColor(Color.parseColor("#efebe9"))
                this.animeStatus.setTextColor(Color.parseColor("#757575"))
            }
        }
    }

    /**
     * Метод настраивает обработчки меню cardviewMenu
     */
    private fun setCardviewMenu() {

        cardviewMenu.setOnClickListener(object: View.OnClickListener {

            override fun onClick(p0: View?) {
                Toast.makeText(mContext, "Cardmenu click", Toast.LENGTH_SHORT).show()
            }
        })
        // TODO 18.04.2017 обработчик меню cardviewMenu (popup)
    }

    /**
     * Метод настраивает ширину картинки
     */
    private fun setPosterLayoutParams(context: Context) {
        this.animePoster.layoutParams.width = ((180 * context.resources.displayMetrics.density / 1.4).toInt())
    }
}