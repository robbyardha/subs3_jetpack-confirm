package com.ardhacodes.subs1_jetpack.ui.tv

import com.ardhacodes.subs1_jetpack.data.source.datalocal.entity.TvEntity

interface TvFragmentCallback {
    fun onItemClick(tvEntity: TvEntity)
}
