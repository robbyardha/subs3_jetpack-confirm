package com.ardhacodes.subs1_jetpack.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.ui.favorite.moviefavorite.MovieFavFragment
import com.ardhacodes.subs1_jetpack.ui.favorite.tvfavorite.TvFavFragment

class SectionsPagerAdapterFavorite(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getCount(): Int {
        return 2
    }
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MovieFavFragment()
            1 -> TvFavFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}