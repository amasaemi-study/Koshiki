package org.shikimori.koshiki.ui.customviews

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.*

import org.shikimori.koshiki.R

/**
 * Created by alex on 19.04.17.
 */
class SearchingBar(val context: Context, rootView: View) : View.OnClickListener {

    val vSearchingControlLayout: View
    val vSearchingStatus: TextView
    val vSearchingKind: TextView
    val vSearchingAgeRating: TextView
    val vSearchingSeason: TextView
    val vSearchingMyList: TextView
    val vSearchingGenres: TextView
    val vSearchingSort: TextView
    val vSearchingSearch: View
    val vSearchingSubstrate: View
    val openParametersItem: View
    val iconExpandMore: View

    var mFindListener: OnFindListener? = null

    interface OnFindListener {
        fun onFind()
    }

    init {
        vSearchingControlLayout = rootView.findViewById(R.id.animelist_parameters_find_container_control_layout)
        vSearchingControlLayout.setOnClickListener(this)
        vSearchingStatus = rootView.findViewById(R.id.animelist_parameters_find_searching_status) as TextView
        vSearchingStatus.setOnClickListener(this)
        vSearchingKind = rootView.findViewById(R.id.animelist_parameters_find_searching_kind) as TextView
        vSearchingKind.setOnClickListener(this)
        vSearchingAgeRating = rootView.findViewById(R.id.animelist_parameters_find_searching_age_rating) as TextView
        vSearchingAgeRating.setOnClickListener(this)
        vSearchingSeason = rootView.findViewById(R.id.animelist_parameters_find_searching_season) as TextView
        vSearchingSeason.setOnClickListener(this)
        vSearchingMyList = rootView.findViewById(R.id.animelist_parameters_find_searching_my_list) as TextView
        vSearchingMyList.setOnClickListener(this)
        vSearchingGenres = rootView.findViewById(R.id.animelist_parameters_find_searching_genres) as TextView
        vSearchingGenres.setOnClickListener(this)
        vSearchingSort = rootView.findViewById(R.id.animelist_parameters_find_searching_sort) as TextView
        vSearchingSort.setOnClickListener(this)
        vSearchingSearch = rootView.findViewById(R.id.animelist_parameters_find_searching_search)
        vSearchingSearch.setOnClickListener(this)
        vSearchingSubstrate = rootView.findViewById(R.id.animelist_parameters_find_substrate)
        vSearchingSubstrate.setOnClickListener(this)
        openParametersItem = rootView.findViewById(R.id.animelist_fragment_open_layout)
        openParametersItem.setOnClickListener(this)
        iconExpandMore = rootView.findViewById(R.id.animelist_fragment_icon_expanded_mode)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.animelist_parameters_find_container_control_layout -> {
                return
            }

            R.id.animelist_parameters_find_searching_status -> {
                // todo
            }

            R.id.animelist_parameters_find_searching_kind -> {
                // todo
            }

            R.id.animelist_parameters_find_searching_age_rating -> {
                // todo
            }

            R.id.animelist_parameters_find_searching_season -> {
                // todo
            }

            R.id.animelist_parameters_find_searching_my_list -> {
                // todo
            }

            R.id.animelist_parameters_find_searching_genres -> {
                // todo
            }

            R.id.animelist_parameters_find_searching_sort -> {
                // todo
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

            // todo обнулить alertdialog's
        } else {
            resetLabels()

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
    private fun resetLabels() {
        vSearchingStatus.text = context.getString(R.string.animelist_finding_paramaters_status)
        vSearchingKind.text = context.getString(R.string.animelist_finding_paramaters_kind)
        vSearchingAgeRating.text = context.getString(R.string.animelist_finding_paramaters_age_rating)
        vSearchingSeason.text = context.getString(R.string.animelist_finding_paramaters_season)
        vSearchingMyList.text = context.getString(R.string.animelist_finding_paramaters_stat_my_list)
        vSearchingGenres.text = context.getString(R.string.animelist_finding_paramaters_genres)
    }

}