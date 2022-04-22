package com.example.yourticketsapp.di

import com.example.yourticketsapp.contract.MainContract
import com.example.yourticketsapp.presenters.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@InstallIn(ActivityComponent::class)
@Module
abstract class MainModule {
    @Binds
    abstract fun bindPresenter(impl: MainPresenter): MainContract.PresenterFragment

}
