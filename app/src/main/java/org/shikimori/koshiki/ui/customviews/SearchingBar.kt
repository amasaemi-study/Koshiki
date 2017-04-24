package org.shikimori.koshiki.ui.customviews

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.*

import org.shikimori.koshiki.R
import org.shikimori.koshiki.ui.dialogs.MoreSelectedDialog
import org.shikimori.koshiki.ui.dialogs.OnCloseDialogListener
import org.shikimori.koshiki.ui.dialogs.SingleSelectedDialog
import org.shikimori.koshiki.utils.ValueParser

/**
 * Created by alex on 19.04.17.
 */
class SearchingBar(val context: Context, rootView: View) : View.OnClickListener {

    private val vSearchingControlLayout = rootView.findViewById(R.id.animelist_parameters_find_container_control_layout)
    private val vSearchingStatus = rootView.findViewById(R.id.animelist_parameters_find_searching_status) as TextView
        private var selectStatusDialog: MoreSelectedDialog? = null
    private val vSearchingKind = rootView.findViewById(R.id.animelist_parameters_find_searching_kind) as TextView
        private var selectKindDialog: MoreSelectedDialog? = null
    private val vSearchingAgeRating = rootView.findViewById(R.id.animelist_parameters_find_searching_age_rating) as TextView
        private var selectAgeRatingDialog: MoreSelectedDialog? = null
    private val vSearchingSeason = rootView.findViewById(R.id.animelist_parameters_find_searching_season) as TextView
        private var selectSeasonDialog: MoreSelectedDialog? = null
    private val vSearchingMyList = rootView.findViewById(R.id.animelist_parameters_find_searching_my_list) as TextView
        private var selectMyListDialog: MoreSelectedDialog? = null
    private val vSearchingGenres = rootView.findViewById(R.id.animelist_parameters_find_searching_genres) as TextView
        private var selectGenresDialog: MoreSelectedDialog? = null
    private val vSearchingSort = rootView.findViewById(R.id.animelist_parameters_find_searching_order) as TextView
        private var selectSortDialog: SingleSelectedDialog? = null
    private val vSearchingSearch = rootView.findViewById(R.id.animelist_parameters_find_searching_search)
    private val vSearchingSubstrate = rootView.findViewById(R.id.animelist_parameters_find_substrate)
    private val openParametersItem = rootView.findViewById(R.id.animelist_fragment_open_layout)
    private val iconExpandMore = rootView.findViewById(R.id.animelist_fragment_icon_expanded_mode)

    init {
        vSearchingControlLayout.setOnClickListener(this)
        vSearchingStatus.setOnClickListener(this)
        vSearchingKind.setOnClickListener(this)
        vSearchingAgeRating.setOnClickListener(this)
        vSearchingSeason.setOnClickListener(this)
        vSearchingMyList.setOnClickListener(this)
        vSearchingGenres.setOnClickListener(this)
        vSearchingSort.setOnClickListener(this)
        vSearchingSearch.setOnClickListener(this)
        vSearchingSubstrate.setOnClickListener(this)
        openParametersItem.setOnClickListener(this)
    }

    var mFindListener: OnFindListener? = null

    interface OnFindListener {
        fun onFind()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.animelist_parameters_find_container_control_layout -> {
                return
            }

            R.id.animelist_parameters_find_searching_status -> {
                showStatusDialog()
            }

            R.id.animelist_parameters_find_searching_kind -> {
                showKindDialog()
            }

            R.id.animelist_parameters_find_searching_age_rating -> {
                showAgeRatingDialog()
            }

            R.id.animelist_parameters_find_searching_season -> {
                showSeasonDialog()
            }

            R.id.animelist_parameters_find_searching_my_list -> {
                showMyListDialog()
            }

            R.id.animelist_parameters_find_searching_genres -> {
                showGenresDialog()
            }

            R.id.animelist_parameters_find_searching_order -> {
                showSortDialog()
            }

            R.id.animelist_parameters_find_searching_search -> {
                mFindListener?.onFind()
                changeFindBarVisibilityState()
            }

            else -> {
                changeFindBarVisibilityState()
            }
        }
    }

    fun showStatusDialog() {
        if (selectStatusDialog == null) {
            selectStatusDialog = MoreSelectedDialog(context, R.array.status,
                    context.getString(R.string.animelist_finding_paramaters_status))
            selectStatusDialog?.setOnCloseDialogListener(object: OnCloseDialogListener {
                override fun onCloseDialog(selectedItems: Any) {
                    if ((selectedItems as IntArray).isNotEmpty())
                        vSearchingStatus.text = ValueParser.getStatusSelected(false, selectedItems)
                    else
                        vSearchingStatus.text = context.getString(R.string.animelist_finding_paramaters_status)
                }
            })
        }

        selectStatusDialog?.show()
    }

    fun getSelectedStatus(): String? {
        return ValueParser.getStatusSelected(true, selectStatusDialog?.getSelectedElements())
    }

    fun showKindDialog() {
        if (selectKindDialog == null) {
            selectKindDialog = MoreSelectedDialog(context, R.array.kind,
                    context.getString(R.string.animelist_finding_paramaters_kind))
            selectKindDialog?.setOnCloseDialogListener(object: OnCloseDialogListener {
                override fun onCloseDialog(selectedItems: Any) {
                    if ((selectedItems as IntArray).isNotEmpty())
                        vSearchingKind.text = ValueParser.getKindSelected(false, selectedItems)
                    else
                        vSearchingKind.text = context.getString(R.string.animelist_finding_paramaters_kind)
                }
            })
        }

        selectKindDialog?.show()
    }

    fun getSelectedKind(): String? {
        return ValueParser.getKindSelected(true, selectKindDialog?.getSelectedElements())
    }

    fun showAgeRatingDialog() {
        if (selectAgeRatingDialog == null) {
            selectAgeRatingDialog = MoreSelectedDialog(context, R.array.rating,
                    context.getString(R.string.animelist_finding_paramaters_age_rating))
            selectAgeRatingDialog?.setOnCloseDialogListener(object: OnCloseDialogListener {
                override fun onCloseDialog(selectedItems: Any) {
                    if ((selectedItems as IntArray).isNotEmpty())
                        vSearchingAgeRating.text = ValueParser.getRatingSelected(false, selectedItems)
                    else
                        vSearchingAgeRating.text = context.getString(R.string.animelist_finding_paramaters_age_rating)
                }
            })
        }

        selectAgeRatingDialog?.show()
    }

    fun getSelectedAgeRating(): String? {
        return ValueParser.getRatingSelected(true, selectAgeRatingDialog?.getSelectedElements())
    }

    fun showSeasonDialog() {
        if (selectSeasonDialog == null) {
            selectSeasonDialog = MoreSelectedDialog(context, R.array.season,
                    context.getString(R.string.animelist_finding_paramaters_season))
            selectSeasonDialog?.setOnCloseDialogListener(object: OnCloseDialogListener {
                override fun onCloseDialog(selectedItems: Any) {
                    if ((selectedItems as IntArray).isNotEmpty())
                        vSearchingSeason.text = ValueParser.getSeasonSelected(false, selectedItems)
                    else
                        vSearchingSeason.text = context.getString(R.string.animelist_finding_paramaters_season)
                }
            })
        }

        selectSeasonDialog?.show()
    }

    fun getSelectedSeason(): String? {
        return ValueParser.getSeasonSelected(true, selectSeasonDialog?.getSelectedElements())
    }

    fun showMyListDialog() {
        if (selectMyListDialog == null) {
            selectMyListDialog = MoreSelectedDialog(context, R.array.mylist,
                    context.getString(R.string.animelist_finding_paramaters_stat_my_list))
            selectMyListDialog?.setOnCloseDialogListener(object: OnCloseDialogListener {
                override fun onCloseDialog(selectedItems: Any) {
                    if ((selectedItems as IntArray).isNotEmpty())
                        vSearchingMyList.text = ValueParser.getMyListSelected(false, selectedItems)
                    else
                        vSearchingMyList.text = context.getString(R.string.animelist_finding_paramaters_stat_my_list)
                }
            })
        }

        selectMyListDialog?.show()
    }

    fun getSelectedMyList(): String? {
        return ValueParser.getMyListSelected(true, selectMyListDialog?.getSelectedElements())
    }

    fun showGenresDialog() {
        if (selectGenresDialog == null) {
            selectGenresDialog = MoreSelectedDialog(context, R.array.genres,
                    context.getString(R.string.animelist_finding_paramaters_genres))
            selectGenresDialog?.setOnCloseDialogListener(object: OnCloseDialogListener {
                override fun onCloseDialog(selectedItems: Any) {
                    if ((selectedItems as IntArray).isNotEmpty())
                        vSearchingGenres.text = ValueParser.getGenresSelected(false, selectedItems)
                    else
                        vSearchingGenres.text = context.getString(R.string.animelist_finding_paramaters_genres)
                }
            })
        }

        selectGenresDialog?.show()
    }

    fun getSelectedGenres(): String? {
        return ValueParser.getGenresSelected(true, selectGenresDialog?.getSelectedElements())
    }

    fun showSortDialog() {
        if (selectSortDialog == null) {
            selectSortDialog = SingleSelectedDialog(context, R.array.order,
                    context.getString(R.string.animelist_finding_paramaters_order))
            selectSortDialog?.setOnCloseDialogListener(object: OnCloseDialogListener {
                override fun onCloseDialog(selectedItems: Any) {
                    vSearchingSort.text = ValueParser.getOrderSelected(false, selectedItems as Int)
                }
            })
        }

        selectSortDialog?.show()
    }

    fun getSelectedSort(): String? {
        return ValueParser.getOrderSelected(true, selectSortDialog?.getSelectedElements())
    }

    /**
     * Метод для настройки коллбэка при нажатии на кнопку поиска
     */
    fun setOnFindLogic(listener: OnFindListener) {
        mFindListener = listener
    }

    /**
     * Возвращает true, если плашка с настройками открыта
     */
    fun isVisible() = (vSearchingControlLayout.visibility == View.VISIBLE)

    /**
     * Метод скрывает и показывает меню для настройки поиска
     */
    fun changeFindBarVisibilityState() {
        //val density = context.resources.displayMetrics.density

        if (isVisible()) {
            // анимация скрытия плашки
            //val animator = ValueAnimator.ofInt(context.resources.getDimension(R.dimen.parameters_layout_height_350dp).toInt(), 0)
            val animator = ValueAnimator.ofFloat(0f, -context.resources.getDimension(R.dimen.parameters_layout_height_350dp))
            animator.addUpdateListener {
                //vSearchingControlLayout.layoutParams.height = (it.animatedValue as Int)
                vSearchingControlLayout.translationY = (it.animatedValue as Float)
                vSearchingControlLayout.requestLayout()
            }

            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    vSearchingSubstrate.animate()
                            .setDuration(450)
                            .alpha(0f)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator?) {
                                    super.onAnimationEnd(animation)
                                    vSearchingSubstrate.visibility = View.GONE
                                }
                            })

                    iconExpandMore.animate()
                            .setDuration(450)
                            .rotation(0f)
                }

                override fun onAnimationEnd(animation: Animator?) {
                    vSearchingControlLayout.visibility = View.GONE
                }

                override fun onAnimationCancel(animation: Animator?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

            animator.interpolator = DecelerateInterpolator()
            animator.duration = 550
            animator.start()
        } else {
            resetLabelsAndDialogs()

            // (350 * density).toInt()
            // анимация показа плашки
            //val animator = ValueAnimator.ofInt(0, context.resources.getDimension(R.dimen.parameters_layout_height_350dp).toInt())
            val animator = ValueAnimator.ofFloat(-context.resources.getDimension(R.dimen.parameters_layout_height_350dp), 0f)
            animator.addUpdateListener {
                //vSearchingControlLayout.layoutParams.height = (it.animatedValue as Int)
                vSearchingControlLayout.translationY = (it.animatedValue as Float)
                vSearchingControlLayout.requestLayout()
            }

            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    vSearchingControlLayout.visibility = View.VISIBLE

                    vSearchingSubstrate.animate()
                            .setDuration(450)
                            .alpha(1f)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationStart(animation: Animator?) {
                                    super.onAnimationStart(animation)
                                    vSearchingSubstrate.visibility = View.VISIBLE
                                }
                            })

                    iconExpandMore.animate()
                            .setDuration(450)
                            .rotation(180f)
                }

                override fun onAnimationEnd(animation: Animator?) {

                }

                override fun onAnimationCancel(animation: Animator?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

            animator.interpolator = DecelerateInterpolator()
            animator.duration = 550
            animator.start()
        }
    }

    /**
     * Возвращает надписи над селекторами к изначальному значению
     */
    private fun resetLabelsAndDialogs() {
        vSearchingStatus.text = context.getString(R.string.animelist_finding_paramaters_status)
        vSearchingKind.text = context.getString(R.string.animelist_finding_paramaters_kind)
        vSearchingAgeRating.text = context.getString(R.string.animelist_finding_paramaters_age_rating)
        vSearchingSeason.text = context.getString(R.string.animelist_finding_paramaters_season)
        vSearchingMyList.text = context.getString(R.string.animelist_finding_paramaters_stat_my_list)
        vSearchingGenres.text = context.getString(R.string.animelist_finding_paramaters_genres)
        vSearchingSort.text = context.getString(R.string.animelist_finding_paramaters_order)

        // TODO подумать над более рациональным использованием памяти диалогов
        selectStatusDialog = null
        selectKindDialog = null
        selectAgeRatingDialog = null
        selectSeasonDialog = null
        selectMyListDialog = null
        selectGenresDialog = null
        selectSortDialog = null
    }
}