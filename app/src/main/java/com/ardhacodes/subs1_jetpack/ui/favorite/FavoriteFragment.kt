package com.ardhacodes.subs1_jetpack.ui.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.databinding.FragmentFavoriteBinding
import com.ardhacodes.subs1_jetpack.ui.favorite.moviefavorite.MovieFavFragment
import com.ardhacodes.subs1_jetpack.ui.favorite.tvfavorite.TvFavFragment
import com.ardhacodes.subs1_jetpack.ui.main.MainActivity
import com.ardhacodes.subs1_jetpack.ui.main.SectionPagerAdapter
import com.ardhacodes.subs1_jetpack.viewmodel.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : DaggerFragment() {

    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding
    private lateinit var viewModel : FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

//    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteBinding?.root
//        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let { configSectionViewPager(it) }
        viewModel = ViewModelProvider(this@FavoriteFragment, factory)[FavoriteViewModel::class.java]
    }

    private fun configSectionViewPager(context: Context) {
        val fragmentList = listOf(MovieFavFragment(), TvFavFragment())
        val tabTitle = listOf(resources.getString(R.string.movie), resources.getString(R.string.tv_show))

        val sectionsPagerAdapterfav = SectionsPagerAdapterFavorite(context, childFragmentManager)
        fragmentFavoriteBinding.viewpager.adapter = sectionsPagerAdapterfav
        fragmentFavoriteBinding.tabLayout2.setupWithViewPager(fragmentFavoriteBinding.viewpager)
    }

}