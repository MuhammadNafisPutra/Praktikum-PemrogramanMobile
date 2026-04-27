package com.example.sahamapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.sahamapp.R
data class Stock(
    @StringRes val nameRes: Int,
    @StringRes val yearRes: Int,
    @StringRes val descRes: Int,
    @DrawableRes val imageRes: Int,
    val webLink: String
)

fun getStockList(): List<Stock> {
    return listOf(
        Stock(R.string.stock_bbca, R.string.year_bbca, R.string.desc_bbca, R.drawable.logo_bbca, "https://www.idx.co.id/id/perusahaan-tercatat/profil-perusahaan-tercatat/BBCA"),
        Stock(R.string.stock_bbri, R.string.year_bbri, R.string.desc_bbri, R.drawable.logo_bbri, "https://www.idx.co.id/id/perusahaan-tercatat/profil-perusahaan-tercatat/BBRI"),
        Stock(R.string.stock_bmri, R.string.year_bmri, R.string.desc_bmri, R.drawable.logo_bmri, "https://www.idx.co.id/id/perusahaan-tercatat/profil-perusahaan-tercatat/BMRI"),
        Stock(R.string.stock_bbni, R.string.year_bbni, R.string.desc_bbni, R.drawable.logo_bbni, "https://www.idx.co.id/id/perusahaan-tercatat/profil-perusahaan-tercatat/BBNI"),
        Stock(R.string.stock_tlkm, R.string.year_tlkm, R.string.desc_tlkm, R.drawable.logo_telkom, "https://www.idx.co.id/id/perusahaan-tercatat/profil-perusahaan-tercatat/TLKM")
    )
}