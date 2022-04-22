package com.example.yourticketsapp.presenters.discover_fragment_presenter

import android.net.Uri
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.yourticketsapp.R
import com.example.yourticketsapp.contract.MainContract
import com.example.yourticketsapp.data.models.ClosePerformancesModel
import com.example.yourticketsapp.data.models.PopularPerformanceModel
import com.example.yourticketsapp.data.models.TheatersModel
import com.example.yourticketsapp.databinding.FragmentDiscoverBinding
import java.net.URL
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import javax.inject.Inject
import android.content.ContentResolver
import android.content.Context
import android.graphics.BitmapFactory

import androidx.annotation.AnyRes


class DiscoverFragmentPresenter @Inject constructor(
    private val showToast: MainContract.MainView
) : MainContract.PresenterFragment {

    private var view: Fragment? = null

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    fun createPopularList(): List<PopularPerformanceModel> {
        val popularList = mutableListOf<PopularPerformanceModel>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            popularList.add(
                PopularPerformanceModel(
                    BitmapFactory.decodeResource(view!!.context?.resources,
                        R.drawable.popular_performance_1),
                    "Волшебная флейта",
                    4.5f,
                    "Опера",
                    "Три дамы, служительницы Царицы ночи, обнаруживают \n" +
                            "в ее лесных владениях принца Тамино и спасают \n" +
                            "его от страшного змея. Бесстрашные амазонки поражены \n" +
                            "красотой юноши и спешат рассказать о нем своей \n" +
                            "госпоже. Неожиданно появляется человек, одетый \n" +
                            "в странный лесной костюм. ",
                    LocalDate.of(12, Month.JANUARY, 21),
                    LocalTime.of(11, 40),
                    "Большой Театр Беларуси"
                )
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            popularList.add(
                PopularPerformanceModel(
                    BitmapFactory.decodeResource(view!!.context?.resources,
                        R.drawable.popular_performance_2),
                    "Иоланта",
                    4f,
                    "Опера",
                    "В роскошном саду вместе с подругами \n" +
                            "и служанками живет дочь короля \n" +
                            "Прованса, прекрасная слепая",
                    LocalDate.of(18, Month.DECEMBER, 21),
                    LocalTime.of(10, 20),
                    "Большой Театр Беларуси"
                )
            )
        }

        return popularList
    }


    fun createCloseList(): List<ClosePerformancesModel> {
        val closeList = mutableListOf<ClosePerformancesModel>()
        closeList.add(
            ClosePerformancesModel(
                ContextCompat.getDrawable(
                    view!!.requireContext(),
                    R.drawable.close_performances_1
                )!!,
                "Анна Каренина"
            )
        )
        closeList.add(
            ClosePerformancesModel(
                ContextCompat.getDrawable(
                    view!!.requireContext(),
                    R.drawable.close_performances_2
                )!!,
                "Евгений Онегин"
            )
        )
        closeList.add(
            ClosePerformancesModel(
                ContextCompat.getDrawable(
                    view!!.requireContext(),
                    R.drawable.close_performances_3
                )!!,
                "Золушка"
            )
        )
        closeList.add(
            ClosePerformancesModel(
                ContextCompat.getDrawable(
                    view!!.requireContext(),
                    R.drawable.close_performances_4
                )!!,
                "Король Вальса"
            )
        )
        return closeList
    }

    fun createTheatersList(): List<TheatersModel> {
        val theatersList = mutableListOf<TheatersModel>()
        theatersList.add(
            TheatersModel(
                ContextCompat.getDrawable(
                    view!!.requireContext(),
                    R.drawable.theater_1
                )!!,
                "Большой театр Беларуси"
            )
        )
        theatersList.add(
            TheatersModel(
                ContextCompat.getDrawable(
                    view!!.requireContext(),
                    R.drawable.theater_2
                )!!,
                "Театр имени Янки Купалы"
            )
        )
        return theatersList
    }


    override fun attachView(fragment: Fragment, mView: View) {
        view = fragment
        _binding = FragmentDiscoverBinding.bind(mView)
    }

    override fun detachView() {
        view = null
        _binding = null
    }


}