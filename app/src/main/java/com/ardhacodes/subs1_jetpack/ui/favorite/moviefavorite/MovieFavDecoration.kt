package com.ardhacodes.subs1_jetpack.ui.favorite.moviefavorite

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MovieFavDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceHeight
            }
            bottom = spaceHeight
        }
    }
}