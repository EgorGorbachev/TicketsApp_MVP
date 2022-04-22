package com.example.yourticketsapp.data.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
data class PopularPerformanceModel(
    val img: Bitmap,
    val title: String,
    val rating: Float,
    val category: String,
    val description: String,
    val dates: LocalDate,
    val time: LocalTime,
    val place: String
) : Parcelable