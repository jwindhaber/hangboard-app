package com.example.hangboard.components

import android.graphics.Color
import android.text.Layout
import com.example.hangboard.components.style.TomorrowNightStyle.*
import com.example.hangboard.timeline.Timeline
import com.example.hangboard.timer.HangboardTimer
import com.example.hangboard.workout.definition.FragmentIdentifier
import com.example.hangboard.workout.util.TimeFormatter.getFormattedTime
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaJustify


@LayoutSpec
object TimerItemSpec {


    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop timeline: Timeline, @State activityText: String, @State exerciseText: String, @State secondsRemaining: Int, @State color: String): Component {
        return Column.create(c)
            .child(
                Row.create(c)
                    .child(
                        Row.create(c)
                            .widthDip(200f)
                            .heightDip(100f)
                    )
                    .child(
                        Row.create(c)
                            .widthDip(200f)
                            .heightDip(100f)
                    )
                    .child(
                        Row.create(c)
                            .widthDip(200f)
                            .heightDip(100f)
                    )
                    .widthDip(600f)
                    .heightDip(100f)
            )
            .child(
                Row.create(c)
                    .widthPercent(100f)
                    .heightDip(120f)
//                    .backgroundColor(Green.color)
                    .alignItems(YogaAlign.CENTER)
                    .child(
                        Text.create(c)
                            .widthPercent(100f)
                            .text(activityText)
                            .textSizeSp(30f)
                            .backgroundColor(Selection.color)
                            .textColor(Blue.color)
                    )
            )
            .child(
                Row.create(c)
                    .widthPercent(100f)
                    .heightDip(120f)
//                    .backgroundColor(Green.color)
                    .alignItems(YogaAlign.CENTER)
                    .child(
                        Text.create(c)
                            .widthPercent(100f)
                            .text(exerciseText)
                            .textSizeSp(80f)
                            .textAlignment(Layout.Alignment.ALIGN_CENTER)
                            .textColor(Color.parseColor(color))
                    )
            )
            .child(
                Row.create(c)
                    .widthPercent(100f)
                    .heightDip(200f)
//                    .backgroundColor(Green.color)
                    .alignItems(YogaAlign.CENTER)
                    .child(
                        Text.create(c)
                            .widthPercent(100f)
                            .text(getFormattedTime(secondsRemaining))
                            .textColor(Color.parseColor(color))
                            .textSizeSp(140f)
                            .textAlignment(Layout.Alignment.ALIGN_CENTER)
                            .textStyle(1)
                    )
            )
            .child(
                Row.create(c)
                    .widthDip(400f)
                    .heightDip(50f)
            )
            .child(
                Row.create(c)
                    .child(
                        Row.create(c)
                            .widthPercent(33.3f)
                            .heightDip(50f)
                            .backgroundColor(Green.color)
                            .alignItems(YogaAlign.CENTER)
                            .child(
                                Text.create(c)
                                    .widthPercent(100f)
                                    .text("PREV")
                                    .textSizeSp(30f)
                                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
                                    .backgroundColor(Selection.color)
                                    .textColor(Blue.color)
//                                    .clickHandler(TimerItem.onOnStartTimer(c))
                            )
                    )
                    .child(
                        Row.create(c)
                            .widthPercent(33.4f)
                            .heightDip(50f)
                            .backgroundColor(Aqua.color)
                            .alignItems(YogaAlign.CENTER)
                            .child(
                                Text.create(c)
                                    .widthPercent(100f)
                                    .text("START")
                                    .textSizeSp(30f)
                                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
                                    .backgroundColor(Selection.color)
                                    .textColor(Blue.color)
                                    .clickHandler(TimerItem.onOnStartTimer(c))
                            )
                    )
                    .child(
                        Row.create(c)
                            .widthPercent(33.3f)
                            .heightDip(50f)
                            .backgroundColor(Yellow.color)
                            .alignItems(YogaAlign.CENTER)
                            .child(
                                Text.create(c)
                                    .widthPercent(100f)
                                    .text("NEXT")
                                    .textSizeSp(30f)
                                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
                                    .backgroundColor(Selection.color)
                                    .textColor(Blue.color)
//                                    .clickHandler(TimerItem.onOnStartTimer(c))
                            )
                    )
                    .widthPercent(100f)
            )
            .widthPercent(100f)
            .heightPercent(100f)
            .justifyContent(YogaJustify.SPACE_BETWEEN)
            .alignItems(YogaAlign.CENTER)
            .backgroundColor(Background.color)
            .build()
    }


    @OnEvent(ClickEvent::class)
    fun onOnStartTimer(c: ComponentContext, @Prop timeline: Timeline) {
        HangboardTimer(timeline) { secondsRemaining: Int, exerciseText: String, color: String, activityText: String ->
            TimerItem.updateSecondsRemainingState(c, secondsRemaining)
            TimerItem.updateExerciseTextState(c, exerciseText)
            TimerItem.updateColorState(c, color)
            TimerItem.updateActivityTextState(c, activityText)

        }.startTimer()
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

    @OnUpdateState
    fun updateActivityTextState(activityText: StateValue<String>, @Param updatedActivityText: String) {
        activityText.set(updatedActivityText)
    }

    @OnCreateInitialState
    fun createInitialState(
        context: ComponentContext,
        exerciseText: StateValue<String>,
        secondsRemaining: StateValue<Int>,
        color: StateValue<String>,
        activityText: StateValue<String>
    ) {
        exerciseText.set("INIT")
        secondsRemaining.set(0)
        color.set(FragmentIdentifier.WORK.color)
        activityText.set("INIT")
    }


}