package com.example.capybarameditation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuoteObj(
    val q: String,
    val a: String
):Parcelable