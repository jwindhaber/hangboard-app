package com.example.hangboard.components.workout

import android.text.Layout
import com.example.hangboard.components.style.TomorrowNightStyle.*
import com.example.hangboard.workout.dto.Exercise
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaJustify


@LayoutSpec
object ExerciseItemSpec {

    private const val TAG = "ExerciseItemSpec"
    private const val ROW_WITH = 160f
    private const val EXERCISE_TEXT_SIZE = 15f
    private const val HIGTH_EXERCISE_CELL = 20f

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop exercise: Exercise, @Prop setNumber: Int): Component {

        return Column.create(c)
            .child(
                Row.create(c)
                    .child(
                        Row.create(c)
                            .widthDip(50f)
                            .heightDip(40f)
                            .backgroundColor(Background.color)
                            .child(
                                Text.create(c)
                                    .text(setNumber.toString())
                                    .textSizeSp(30f)
                                    .textColor(Blue.color)
                            )
                            .alignItems(YogaAlign.CENTER)
                            .justifyContent(YogaJustify.CENTER)
                    )
                    .child(
                        Column.create(c)
                            .child(
                                Row.create(c)
                                    .child(
                                        Row.create(c)
                                            .widthDip(ROW_WITH)
                                            .heightDip(HIGTH_EXERCISE_CELL)
                                            .backgroundColor(Background.color)
                                            .child(
                                                Text.create(c)
                                                    .text("Reps: ${exercise.repetitions}")
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .textColor(Blue.color)
                                            )
                                            .alignItems(YogaAlign.CENTER)
                                            .justifyContent(YogaJustify.CENTER)
                                    )
                                    .child(
                                        Row.create(c)
                                            .widthDip(ROW_WITH)
                                            .heightDip(HIGTH_EXERCISE_CELL)
                                            .backgroundColor(Background.color)
                                            .child(
                                                Text.create(c)
                                                    .text("Weight: ${exercise.weight}")
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .textColor(Blue.color)
                                            )
                                            .alignItems(YogaAlign.CENTER)
                                            .justifyContent(YogaJustify.CENTER)
                                    )
                            )
                            .child(
                                Row.create(c)
                                    .child(
                                        Row.create(c)
                                            .widthDip(ROW_WITH)
                                            .heightDip(HIGTH_EXERCISE_CELL)
                                            .backgroundColor(Background.color)
                                            .child(
                                                Text.create(c)
                                                    .text("Work: ${exercise.workUnit.work} sec")
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .textColor(Red.color)
                                            )
                                            .alignItems(YogaAlign.CENTER)
                                            .justifyContent(YogaJustify.CENTER)
                                    )
                                    .child(
                                        Row.create(c)
                                            .widthDip(ROW_WITH)
                                            .heightDip(HIGTH_EXERCISE_CELL)
                                            .backgroundColor(Background.color)
                                            .child(
                                                Text.create(c)
                                                    .text("Rest: ${exercise.workUnit.rest} sec")
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .textColor(Green.color)
                                            )
                                            .alignItems(YogaAlign.CENTER)
                                            .justifyContent(YogaJustify.CENTER)
                                    )
                            )
                    )
                    .child(
                        Row.create(c)
                            .widthDip(50f)
                            .heightDip(40f)
                            .backgroundColor(Background.color)
                    )
            )
            .child(
                Row.create(c)
                    .widthDip(500f)
                    .heightDip(30f)
                    .backgroundColor(CurrentLine.color)
                    .child(
                        Text.create(c)
                            .text(exercise.rest.toString()+" Seconds")
                            .textSizeSp(EXERCISE_TEXT_SIZE)
                            .textColor(Green.color)
                            .textAlignment(Layout.Alignment.ALIGN_CENTER)
                    )
                    .justifyContent(YogaJustify.CENTER)
                    .alignItems(YogaAlign.CENTER)
            )
            .alignItems(YogaAlign.CENTER)
            .backgroundColor(Background.color)
            .build()
    }






//        
//        val TEXTSIZE = 20f
//        return Row.create(c)
//            .child(
//                Row.create(c)
//                    .widthDip(50ff)
//                    .heightDip(100ff)
//                    .border(Border.create(c).color(YogaEdge.ALL, Color.BLACK).build())
//                    .child(
//                        Text.create(c)
//                            .text("1")
//                            .textSizeSp(40f)
//                            .textColor(TomorrowNightStyle.Blue.color)
//                    )
//            )
//            .child(
//                Column.create(c)
//                    .child(
//                        Row.create(c)
//                            .child(
//                                Row.create(c)
//                                    .widthDip(130ff)
//                                    .heightDip(50ff)
//                                    .border(Border.create(c).color(YogaEdge.ALL, Color.BLACK).build())
//                                    .child(
//                                        Text.create(c)
//                                            .text("Reps: 7")
//                                            .textSizeSp(TEXTSIZE)
//                                            .textColor(TomorrowNightStyle.Blue.color)
//                                    )
//                                    .child(
//                                        Row.create(c)
//                                            .widthDip(130ff)
//                                            .heightDip(50ff)
//                                            .border(Border.create(c).color(YogaEdge.ALL, Color.BLACK).build())
//                                            .child(
//                                                Text.create(c)
//                                                    .text("Weight: 35 kg")
//                                                    .textSizeSp(TEXTSIZE)
//                                                    .textColor(TomorrowNightStyle.Blue.color)
//                                            )
//                                    )
//                                    .widthDip(400ff)
//                                    .heightDip(100ff)
//                            )
//                            .child(
//                                Row.create(c)
//                                    .widthDip(130ff)
//                                    .heightDip(50ff)
//                                    .border(Border.create(c).color(YogaEdge.ALL, Color.BLACK).build())
//                                    .child(
//                                        Text.create(c)
//                                            .text("Workunit")
//                                            .textSizeSp(TEXTSIZE)
//                                            .textColor(TomorrowNightStyle.Blue.color)
//                                    )
//                                    .child(
//                                        Row.create(c)
//                                            .widthDip(130ff)
//                                            .heightDip(50ff)
//                                            .border(Border.create(c).color(YogaEdge.ALL, Color.BLACK).build())
//                                            .child(
//                                                Text.create(c)
//                                                    .text("Work: 7 sec")
//                                                    .textSizeSp(TEXTSIZE)
//                                                    .textColor(TomorrowNightStyle.Blue.color)
//                                            )
//                                    )
//                                    .widthDip(400ff)
//                                    .heightDip(100ff)
//                            )
//                            .widthDip(400ff)
//                            .heightDip(100ff)
//                    )
//            )
//            .child(
//                Row.create(c)
//                    .widthDip(60f)
//                    .heightDip(100ff)
//                    .border(Border.create(c).color(YogaEdge.ALL, Color.BLACK).build())
//            )
//
//            .widthDip(50f0f)
//            .heightDip(50f0f)
//            .border(Border.create(c).color(YogaEdge.ALL, Color.BLACK).build())
//            .build()
//    }
}

//    @OnEvent(ClickEvent::class)
//    fun onClickHangboard(c: ComponentContext, @FromEvent view: View, @Prop listener: HomeComponentClickListener) {
//        Log.i(HomeComponentSpec.TAG, "Hangboard clicked")
//        listener.onHangboardClick()
//    }
//
//    @OnEvent(ClickEvent::class)
//    fun onClickHistory(c: ComponentContext, @FromEvent view: View, @Prop listener: HomeComponentClickListener) {
//        Log.i(HomeComponentSpec.TAG, "History clicked")
//        listener.onHistoryClick()
//    }
//
//
//    interface HomeComponentClickListener {
//        fun onHangboardClick()
//        fun onHistoryClick()
//    }

