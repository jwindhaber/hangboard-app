package com.example.hangboard.components.beeps

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.SystemClock
import android.view.View
import com.example.hangboard.components.style.TomorrowNightStyle.Background
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.FromEvent
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.widget.Text


@LayoutSpec
object CustomBeepSpec {

//    val toneGenerator = ToneGenerator(AudioManager.STREAM_ALARM, 100)
    val toneGenerator = ToneGenerator(AudioManager.STREAM_ALARM, 100)


    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext): Component  {

        return Column.create(c).
            child(
                Text.create(c)
                    .text("custome")
                    .textSizeSp(25f)
                    .textColor(Background.color)
                    .clickHandler(CustomBeep.onClick(c))
//                    .heightDip(300f)

            ).build()

        }



    @OnEvent(ClickEvent::class)
    fun onClick(c: ComponentContext, @FromEvent view: View) {


        soundOnEndHold()
    }


    fun soundOnEndHold(){

        val handler = Handler()
        val timeNow = SystemClock.uptimeMillis()

        handler.postAtTime({
            toneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 375)
        }, 0 + timeNow)

        handler.postAtTime({
            toneGenerator.startTone(ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE, 120)
        }, 880 + timeNow)

//675
//        ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, "TONE_CDMA_ALERT_CALL_GUARD") // Perfect double
//        ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE, "TONE_CDMA_KEYPAD_VOLUME_KEY_LITE") //perfect one timer
//        ToneGenerator.TONE_CDMA_PIP, "TONE_CDMA_PIP")

    }


}


