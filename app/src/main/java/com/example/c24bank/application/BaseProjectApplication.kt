package com.example.c24bank.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class C24BankApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}