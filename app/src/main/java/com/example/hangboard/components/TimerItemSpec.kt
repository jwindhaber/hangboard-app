package com.example.hangboard.components

import android.graphics.Color
import android.text.Layout
import com.example.hangboard.components.button.Button
import com.example.hangboard.components.button.ButtonItem
import com.example.hangboard.components.button.ButtonItemSpec
import com.example.hangboard.components.button.ButtonSpec
import com.example.hangboard.components.style.TomorrowNightStyle
import com.example.hangboard.timeline.Timeline
import com.example.hangboard.timer.HangboardTimer
import com.example.hangboard.workout.definition.FragmentIdentifier
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.StateValue
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaEdge.ALL


@LayoutSpec
object TimerItemSpec {



    @OnCreateLayout
    fun onCreateLayout(context: ComponentContext, @Prop timeline: Timeline, @State exerciseText: String, @State secondsRemaining: Int, @State color: String): Component {

        return Column.create(context)
            .paddingDip(ALL, 16f)
            .backgroundColor(TomorrowNightStyle.CurrentLine.color)
            .child(
                Text.create(context)
                    .text(exerciseText)
                    .textColor(Color.parseColor(color))
                    .textSizeSp(40f)
                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
            )
            .child(
                Text.create(context)
                    .text(secondsRemaining.toString())
                    .textColor(Color.parseColor(color))
                    .textSizeSp(60f)
                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
                    .textStyle(1)
            )
            .child(
                ButtonItem.create(context)
                    .text("Start")
                    .listener(object : ButtonItemSpec.OnButtonClickListener {
                        override fun onButtonClick() {

                            HangboardTimer(timeline) { secondsRemaining:Int, exerciseText:String, color:String  ->
                                TimerItem.updateSecondsRemainingState(context, secondsRemaining)
                                TimerItem.updateExerciseTextState(context, exerciseText)
                                TimerItem.updateColorState(context, color)

                            }.startTimer()
                        }
                    })
            )
            .child(
                Button.create(context)
                    .listener(object : ButtonSpec.OnButtonClickListener {
                        override fun onButtonClick() {

                            HangboardTimer(timeline) { secondsRemaining:Int, exerciseText:String, color:String  ->
                                TimerItem.updateSecondsRemainingState(context, secondsRemaining)
                                TimerItem.updateExerciseTextState(context, exerciseText)
                                TimerItem.updateColorState(context, color)

                            }.startTimer()
                        }
                    })
                    .widthDip(100f)
                    .heightDip(48f)
                    .marginDip(YogaEdge.RIGHT, 16f)
                    .clickHandler(Button.onClick(context))

            )
            .build()
    }


    @OnUpdateState
    fun updateSecondsRemainingState(secondsRemaining: StateValue<Int>, @Param updatedSecondsRemaining: Int) {
        secondsRemaining.set(updatedSecondsRemaining)
    }
    @OnUpdateState
    fun updateColorState(color: StateValue<String>, @Param updatedColor: String) {
        color.set(updatedColor)
    }
    @OnUpdateState
    fun updateExerciseTextState(exerciseText: StateValue<String>, @Param updatedExerciseText: String) {
        exerciseText.set(updatedExerciseText)
    }

    @OnCreateInitialState
    fun createInitialState(context: ComponentContext, exerciseText: StateValue<String>, secondsRemaining: StateValue<Int>, color: StateValue<String>) {
        exerciseText.set("INIT")
        secondsRemaining.set(0)
        color.set(FragmentIdentifier.WORK.color)
    }


}