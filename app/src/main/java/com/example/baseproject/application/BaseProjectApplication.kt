package com.example.baseproject.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseProjectApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}