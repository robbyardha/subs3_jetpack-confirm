package com.ardhacodes.subs1_jetpack.ui.favorite.tvfavorite

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.databinding.FragmentTvFavBinding
import com.ardhacodes.subs1_jetpack.ui.detail.DetailMovieTvActivity
import com.ardhacodes.subs1_jetpack.ui.detail.DetailViewModel
import com.ardhacodes.subs1_jetpack.ui.detail.DetailViewModel.Companion.TV_VIEWMDL
import com.ardhacodes.subs1_jetpack.ui.favorite.FavoriteViewModel
import com.ardhacodes.subs1_jetpack.ui.tv.TvAdapter
import com.ardhacodes.subs1_jetpack.ui.tv.TvFragmentCallback
import com.ardhacodes.subs1_jetpack.utils.Helper
import com.ardhacodes.subs1_jetpack.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tv_fav.*
import kotlinx.android.synthetic.main.message_empty.*
import javax.inject.Inject

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TvFavFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TvFavFragment : DaggerFragment() , TvFragmentCallback {

    private lateinit var fragmentTvFavBinding: FragmentTvFavBinding
    private lateinit var adapter: TvAdapter
    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTvFavBinding = FragmentTvFavBinding.inflate(layoutInflater, container, false)
        return fragmentTvFavBinding?.root
//        return inflater.inflate(R.layout.fragment_tv_fav, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configRecycleView()
        parentFragmentVMProvider()
        getFavTvList()
    }

    private fun configRecycleView(){
        fragmentTvFavBinding.rvFavtv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TvAdapter(this@TvFavFragment)
        }
    }


    private fun parentFragmentVMProvider(){
        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavoriteViewModel::class.java]
        }
    }

    private fun getFavTvList(){
        viewModel.getFavTvList().observe(viewLifecycleOwner, Observer {
            var rvFavTvBind = fragmentTvFavBinding.rvFavtv
            if (it != null){
                rvFavTvBind.adapter?.let { adapter ->
                    when(adapter){
                        is TvAdapter -> {
                            if (it.isNullOrEmpty()){
                                rvFavTvBind.visibility = View.GONE
                            }else{
                                rvFavTvBind.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onItemClick(tvEntity: TvEntity) {
        startActivity(
            Intent(
                context,
                DetailMovieTvActivity::class.java
            )
                .putExtra(DetailMovieTvActivity.EXTRA_MOV, tvEntity.idtv)
                .putExtra(DetailMovieTvActivity.EXTRA_CATEGORY, Helper.TYPE_TVSHOW)
        )
    }

}