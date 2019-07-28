package com.example.hangboard.config.persistence

import android.content.Context
import io.paperdb.Paper

class PersistenceConfiguration {

    companion object PaperInitializer{
        fun initPaper(context: Context){
            //loader for paper
            Paper.init(context);
        }
    }

}