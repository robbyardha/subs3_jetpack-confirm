package com.ardhacodes.subs1_jetpack.ui.favorite.moviefavorite

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.MovieEntity
import com.ardhacodes.subs1_jetpack.databinding.FragmentMovieFavBinding
import com.ardhacodes.subs1_jetpack.ui.detail.DetailMovieTvActivity
import com.ardhacodes.subs1_jetpack.ui.detail.DetailViewModel.Companion.MOVIE_VIEWMDL
import com.ardhacodes.subs1_jetpack.ui.favorite.FavoriteViewModel
import com.ardhacodes.subs1_jetpack.ui.movie.CallbackMov
import com.ardhacodes.subs1_jetpack.ui.movie.MovieAdapter
import com.ardhacodes.subs1_jetpack.utils.Helper
import com.ardhacodes.subs1_jetpack.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_fav.*
import kotlinx.android.synthetic.main.message_empty.*
import javax.inject.Inject

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFavFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFavFragment : DaggerFragment(), CallbackMov {
    private lateinit var fragmentFavMovBinding : FragmentMovieFavBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: MovieFavAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentFavMovBinding = FragmentMovieFavBinding.inflate(layoutInflater, container, false)
        return fragmentFavMovBinding?.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configRecycleView()
        getParentFragmentFav()
        //observeFavoriteMovie()
        getFavMovListVM()
    }


    private fun configRecycleView(){
        fragmentFavMovBinding.rvFavmovie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(this@MovieFavFragment)
        }
    }

    public fun getParentFragmentFav(){
        parentFragment?.let{
            viewModel = ViewModelProvider(it, factory)[FavoriteViewModel::class.java]
        }
    }

    public fun getFavMovListVM(){
        viewModel.getFavMovieList().observe(viewLifecycleOwner, Observer {
            var rvFavMovBind = fragmentFavMovBinding.rvFavmovie
            if (it != null){
                rvFavMovBind.adapter?.let { adapter ->
                    when(adapter){
                        is MovieAdapter -> {
                            if(it.isNullOrEmpty()){
                                rvFavMovBind.visibility = View.GONE
                            }else{
                                rvFavMovBind.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onItemClicked(movieEntity: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailMovieTvActivity::class.java
            )
                .putExtra(DetailMovieTvActivity.EXTRA_MOV, movieEntity.idmovie)
                .putExtra(DetailMovieTvActivity.EXTRA_CATEGORY, Helper.TYPE_MOVIE)
        )
    }
}