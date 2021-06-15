package com.ardhacodes.subs1_jetpack.ui.movie

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
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.data.MovieTvEntity
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.databinding.FragmentMovieBinding
import com.ardhacodes.subs1_jetpack.ui.CallbackMovTv
import com.ardhacodes.subs1_jetpack.ui.detail.DetailMovieTvActivity
import com.ardhacodes.subs1_jetpack.ui.detail.DetailViewModel.Companion.MOVIE_VIEWMDL
import com.ardhacodes.subs1_jetpack.ui.main.MainActivity
import com.ardhacodes.subs1_jetpack.ui.main.MainViewModel
import com.ardhacodes.subs1_jetpack.utils.Helper
import com.ardhacodes.subs1_jetpack.utils.Helper.EXTRA_MOVIE
import com.ardhacodes.subs1_jetpack.utils.MoviesTvDataDummy
import com.ardhacodes.subs1_jetpack.utils.SortUtils.VOTE_BEST
import com.ardhacodes.subs1_jetpack.viewmodel.ViewModelFactory
import com.ardhacodes.subs1_jetpack.vo.Resource
import com.ardhacodes.subs1_jetpack.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : DaggerFragment(), CallbackMov {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setupRecycle()
        activity?.let { viewModelProvider(it) }
        getPopularMovieObserve()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val adapter = MovieAdapter(this@MovieFragment)

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onItemClicked(movieEntity: MovieEntity) {
        startActivity(
            Intent(context, DetailMovieTvActivity::class.java)
                .putExtra(DetailMovieTvActivity.EXTRA_MOV, movieEntity.idmovie)
                .putExtra(DetailMovieTvActivity.EXTRA_CATEGORY, Helper.TYPE_MOVIE)
        )
    }

    private fun viewModelProvider(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory)[MainViewModel::class.java]
    }

    private fun getPopularMovieObserve() {
        viewModel.getPopularMovie().observe(viewLifecycleOwner, Observer { listMovie ->
            var progresBarBind = binding.progressBar
            var rvMovBind = binding.rvMovie
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> progresBarBind.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progresBarBind.visibility = View.GONE
                        rvMovBind.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        progresBarBind.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "Tidak Ada Koneksi Internet",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }
}