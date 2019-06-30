package com.example.hangboard.components.beeps

import android.media.AudioManager
import android.media.ToneGenerator
import android.view.View
import com.example.hangboard.components.style.TomorrowNightStyle.Background
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text


@LayoutSpec
object BeepSpec {

//    val toneGenerator = ToneGenerator(AudioManager.STREAM_ALARM, 100)
    val toneGenerator = ToneGenerator(AudioManager.STREAM_ALARM, 100)


    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop tone: Int, @Prop name: String): Component  {

        return Column.create(c).
            child(
                Text.create(c)
                    .text(name)
                    .textSizeSp(15f)
                    .textColor(Background.color)
                    .clickHandler(Beep.onClick(c,tone))
//                    .heightDip(300f)

            ).build()

        }



    @OnEvent(ClickEvent::class)
    fun onClick(c: ComponentContext, @FromEvent view: View, @Param tone: Int) {
        toneGenerator.startTone(tone, 500)
    }




}


