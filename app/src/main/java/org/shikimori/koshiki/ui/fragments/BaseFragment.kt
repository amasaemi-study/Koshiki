package org.shikimori.koshiki.ui.fragments

import android.app.Fragment

/**
 * Created by Александр on 18.04.2017.
 */
abstract class BaseFragment : Fragment(), OnBackPressedFragment {
    override fun onBackPressed(): Boolean {
        return true;
    }
}