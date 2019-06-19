package com.example.hangboard

import android.app.Application
import com.facebook.soloader.SoLoader

class HangboardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)
    }
}