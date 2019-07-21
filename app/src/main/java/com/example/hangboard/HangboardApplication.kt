package com.example.hangboard

import android.app.Application
import com.facebook.soloader.SoLoader
import io.paperdb.Paper

class HangboardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //loader for Litho
        SoLoader.init(this, false)

        //loader for paper
        Paper.init(applicationContext);
    }
}