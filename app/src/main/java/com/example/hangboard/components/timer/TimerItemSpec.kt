package com.example.hangboard.components.timer

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
    fun onCreateLayout(c: ComponentContext, @Prop timeline: Timeline, @State timerState: TimerState): Component {
        return Column.create(c)

            .child(
                Row.create(c)
                    .widthPercent(100f)
                    .heightDip(50f)
                    .backgroundColor(Yellow.color)
                    .alignItems(YogaAlign.CENTER)
                    .child(
                        Text.create(c)
                            .widthPercent(100f)
                            .text(timerState.exerciseName)
                            .textSizeSp(30f)
//                            .textAlignment(Layout.Alignment.ALIGN_LEFT)
                            .backgroundColor(Selection.color)
                            .textColor(Blue.color)
                    )
            )
            .child(
                Row.create(c)
                    .child(
                        Row.create(c)
                            .widthPercent(33.3f)
                            .heightDip(40f)
                            .backgroundColor(Yellow.color)
                            .alignItems(YogaAlign.CENTER)
                            .child(
                                Text.create(c)
                                    .widthPercent(100f)
                                    .text("Weight")
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
                            .heightDip(40f)
                            .backgroundColor(Yellow.color)
                            .alignItems(YogaAlign.CENTER)
                            .child(
                                Text.create(c)
                                    .widthPercent(100f)
                                    .text("Exercise")
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
                            .heightDip(40f)
                            .backgroundColor(Yellow.color)
                            .alignItems(YogaAlign.CENTER)
                            .child(
                                Text.create(c)
                                    .widthPercent(100f)
                                    .text("Time")
                                    .textSizeSp(30f)
                                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
                                    .backgroundColor(Selection.color)
                                    .textColor(Blue.color)
//                                    .clickHandler(TimerItem.onOnStartTimer(c))
                            )
                    )
                    .widthPercent(100f)
            )
            .child(
                Row.create(c)
                    .child(
                        Row.create(c)
                            .widthPercent(33.3f)
                            .heightDip(40f)
                            .backgroundColor(Yellow.color)
                            .alignItems(YogaAlign.CENTER)
                            .child(
                                Text.create(c)
                                    .widthPercent(100f)
                                    .text("${timerState.weight}")
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
                            .heightDip(40f)
                            .backgroundColor(Yellow.color)
                            .alignItems(YogaAlign.CENTER)
                            .child(
                                Text.create(c)
                                    .widthPercent(100f)
                                    .text(timerState.numberExercises)
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
                            .heightDip(40f)
                            .backgroundColor(Yellow.color)
                            .alignItems(YogaAlign.CENTER)
                            .child(
                                Text.create(c)
                                    .widthPercent(100f)
                                    .text(getFormattedTime(timerState.overallSecondsLeft))
                                    .textSizeSp(30f)
                                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
                                    .backgroundColor(Selection.color)
                                    .textColor(Blue.color)
//                                    .clickHandler(TimerItem.onOnStartTimer(c))
                            )
                    )
                    .widthPercent(100f)
            )
//            .child(
//                Row.create(c)
//                    .widthDip(400f)
//                    .heightDip(100f)
//            )
            .child(
                Row.create(c)
                    .widthPercent(100f)
                    .heightDip(140f)
//                    .backgroundColor(Green.color)
                    .alignItems(YogaAlign.CENTER)
                    .child(
                        Text.create(c)
                            .widthPercent(100f)
                            .text(timerState.workState)
                            .textSizeSp(100f)
                            .textAlignment(Layout.Alignment.ALIGN_CENTER)
                            .textColor(Color.parseColor(timerState.color))
                    )
            )
            .child(
                Row.create(c)
                    .widthPercent(100f)
                    .heightDip(230f)
//                    .backgroundColor(Green.color)
                    .alignItems(YogaAlign.CENTER)
                    .child(
                        Text.create(c)
                            .widthPercent(100f)
                            .text(getFormattedTime(timerState.timer))
                            .textColor(Color.parseColor(timerState.color))
                            .textSizeSp(170f)
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
                            .backgroundColor(Yellow.color)
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
                            .backgroundColor(Yellow.color)
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
        HangboardTimer(timeline) { timerState ->
            TimerItem.updateTimerState(c, timerState)

        }.startTimer()
    }

    @OnUpdateState
    fun updateTimerState(timerState: StateValue<TimerState>, @Param updatedTimerState: TimerState) {
        timerState.set(updatedTimerState)
    }

    @OnCreateInitialState
    fun createInitialState(c: ComponentContext, timerState: StateValue<TimerState>) {
        timerState.set(TimerState(0, "INIT", FragmentIdentifier.WORK.color, "INIT", "0", 0, "1/1"))

    }


}