package com.example.hangboard

import android.app.Application
import com.example.hangboard.config.persistence.PersistenceConfiguration
import com.facebook.soloader.SoLoader

class HangboardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //loader for Litho
        SoLoader.init(this, false)

        //loader for paper
        PersistenceConfiguration.initPaper(applicationContext)
    }
}