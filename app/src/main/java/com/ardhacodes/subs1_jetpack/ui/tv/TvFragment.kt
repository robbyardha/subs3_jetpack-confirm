package com.ardhacodes.subs1_jetpack.ui.tv

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.data.MovieTvEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity
import com.ardhacodes.subs1_jetpack.databinding.FragmentMovieBinding
import com.ardhacodes.subs1_jetpack.databinding.FragmentTvBinding
import com.ardhacodes.subs1_jetpack.ui.CallbackMovTv
import com.ardhacodes.subs1_jetpack.ui.detail.DetailMovieTvActivity
import com.ardhacodes.subs1_jetpack.ui.detail.DetailViewModel.Companion.TV_VIEWMDL
import com.ardhacodes.subs1_jetpack.ui.main.MainActivity
import com.ardhacodes.subs1_jetpack.ui.main.MainViewModel
import com.ardhacodes.subs1_jetpack.ui.movie.MovieAdapter
import com.ardhacodes.subs1_jetpack.ui.movie.MovieDecoration
import com.ardhacodes.subs1_jetpack.ui.movie.MovieViewModel
import com.ardhacodes.subs1_jetpack.utils.Helper
import com.ardhacodes.subs1_jetpack.utils.MoviesTvDataDummy
import com.ardhacodes.subs1_jetpack.utils.SortUtils
import com.ardhacodes.subs1_jetpack.utils.SortUtils.VOTE_BEST
import com.ardhacodes.subs1_jetpack.viewmodel.ViewModelFactory
import com.ardhacodes.subs1_jetpack.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class TvFragment : DaggerFragment(), TvFragmentCallback {

    lateinit var binding: FragmentTvBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TvAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { viewModelProviderConfig(it) }
        getPopularTvObserve()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val adapter = TvAdapter(this@TvFragment)

            with(binding.rvTv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    private fun viewModelProviderConfig(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory)[MainViewModel::class.java]
    }


    private fun showProgressBar(state: Boolean) {
        binding.progressBar.isVisible = state
        binding.rvTv.isInvisible = state
    }

    private fun getPopularTvObserve(){
        viewModel.getPopularTv().observe(viewLifecycleOwner, Observer { listTvShow ->
            if(listTvShow != null){
                var progresBarBind = binding.progressBar
                var rvTvBind = binding.rvTv
                when (listTvShow.status){
                    Status.LOADING -> progresBarBind.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progresBarBind.visibility = View.GONE
                        rvTvBind.adapter?.let {
                                adapter ->
                            when(adapter){
                                is TvAdapter ->{
                                    adapter.submitList(listTvShow.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        progresBarBind.visibility = View.GONE
                        Toast.makeText(context, "Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show()
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

//        val intent = Intent(context, DetailMovieTvActivity::class.java)
//        intent.putExtra(DetailMovieTvActivity.EXTRA_MOV, id)
//        intent.putExtra(DetailMovieTvActivity.EXTRA_CATEGORY, TV_VIEWMDL)
//
//        context?.startActivity(intent)
    }
}