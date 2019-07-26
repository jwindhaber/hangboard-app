package com.example.hangboard.components.workout

import android.content.res.ColorStateList.valueOf
import android.text.InputFilter
import android.text.InputType
import android.text.Layout
import android.widget.Toast
import com.example.hangboard.components.style.TomorrowNightStyle.*
import com.example.hangboard.persistence.Exercise
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.TextChangedEvent
import com.facebook.litho.widget.TextInput
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaJustify


/*
TODO maybe do the lazy update later on

Lazy state update will dispatch the state update, but will only apply it in the next layout
pass. See the log statement printed in the `OnCreateLayout`.

ExerciseItem.lazyUpdateExercise(c, ex)
*/


@LayoutSpec
object ExerciseItemSpec {

    private const val TAG = "ExerciseItemSpec"
    private const val ROW_WITH = 160f
    private const val EXERCISE_TEXT_SIZE = 15f
    private const val HEIGHT_EXERCISE_CELL = 20f
    private const val HINT = "0000"

    private val lenFilter = InputFilter.LengthFilter(4)

    @OnCreateInitialState
    fun onCreateInitialState(c: ComponentContext, exercise: StateValue<Exercise>, @Prop initialExercise: Exercise) {
        exercise.set(initialExercise)
    }

    // (canUpdateLazily = true)
    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @State exercise: Exercise, @Prop setNumber: Int, @Prop editable: Boolean): Component {

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
                                            .heightDip(HEIGHT_EXERCISE_CELL)
                                            .backgroundColor(Background.color)
                                            .child(
                                                Text.create(c)
                                                    .text("Reps: ")
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .textColor(Blue.color)
                                            )
                                            .child(
                                                TextInput.create(c)
                                                    .initialText(exercise.repetitions.toString())
                                                    .textColorStateList(valueOf(Blue.color))
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .inputType(InputType.TYPE_CLASS_NUMBER)
                                                    .inputBackgroundRes(0)
                                                    .editable(editable)
                                                    .inputFilter(lenFilter)
                                                    .hint(HINT)
                                                    .hintColorStateList(valueOf(Blue.color))
                                                    .textChangedEventHandler(ExerciseItem.onChangeExerciseRepetitions(c))
                                                    .build()
                                            )
                                            .alignItems(YogaAlign.CENTER)
                                            .justifyContent(YogaJustify.CENTER)
                                    )
                                    .child(
                                        Row.create(c)
                                            .widthDip(ROW_WITH)
                                            .heightDip(HEIGHT_EXERCISE_CELL)
                                            .backgroundColor(Background.color)
                                            .child(
                                                Text.create(c)
                                                    .text("Weight: ")
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .textColor(Blue.color)
                                            )
                                            .child(
                                                TextInput.create(c)
                                                    .initialText(exercise.weight.toString())
                                                    .textColorStateList(valueOf(Blue.color))
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .inputType(InputType.TYPE_NUMBER_FLAG_SIGNED or InputType.TYPE_CLASS_NUMBER)
                                                    .inputBackgroundRes(0)
                                                    .editable(editable)
                                                    .inputFilter(lenFilter)
                                                    .hint(HINT)
                                                    .hintColorStateList(valueOf(Blue.color))
                                                    .textChangedEventHandler(ExerciseItem.onChangeExerciseWeight(c))
                                                    .build()
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
                                            .heightDip(HEIGHT_EXERCISE_CELL)
                                            .backgroundColor(Background.color)
                                            .child(
                                                Text.create(c)
                                                    .text("Work: ")
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .textColor(Red.color)
                                            )
                                            .child(
                                                TextInput.create(c)
                                                    .initialText(exercise.workUnit.work.toString())
                                                    .textColorStateList(valueOf(Red.color))
                                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                                    .inputType(InputType.TYPE_CLASS_NUMBER)
                                                    .inputBackgroundRes(0)
                                                    .editable(editable)
                                                    .inputFilter(lenFilter)
                                                    .hint(HINT)
                                                    .hintColorStateList(valueOf(Red.color))
                                                    .textChangedEventHandler(ExerciseItem.onChangeExerciseWorkUnitWork(c))
                                                    .build()
                                            )
                                            .alignItems(YogaAlign.CENTER)
                                            .justifyContent(YogaJustify.CENTER)
                                    )
                                    .child(
                                        Row.create(c)
                                            .widthDip(ROW_WITH)
                                            .heightDip(HEIGHT_EXERCISE_CELL)
                                            .backgroundColor(Background.color)
                                            .child(
                                                Row.create(c)
                                                    .child(
                                                        Text.create(c)
                                                            .text("Rest: ")
                                                            .textSizeSp(EXERCISE_TEXT_SIZE)
                                                            .textColor(Green.color)
                                                    )
                                                    .child(
                                                        TextInput.create(c)
                                                            .initialText(exercise.workUnit.rest.toString())
                                                            .textColorStateList(valueOf(Green.color))
                                                            .textSizeSp(EXERCISE_TEXT_SIZE)
                                                            .inputType(InputType.TYPE_CLASS_NUMBER)
                                                            .inputBackgroundRes(0)
                                                            .editable(editable)
                                                            .inputFilter(lenFilter)
                                                            .hint(HINT)
                                                            .hintColorStateList(valueOf(Green.color))
                                                            .textChangedEventHandler(ExerciseItem.onChangeExerciseWorkUnitRest(c))
                                                            .build()
                                                    )
                                            )
                                            .alignItems(YogaAlign.CENTER)
                                            .justifyContent(YogaJustify.CENTER)
                                    )
                            )
                    )
                    .alignItems(YogaAlign.CENTER)
                    .justifyContent(YogaJustify.CENTER)
                    .child(
                        Row.create(c)
                            .widthDip(50f)
                            .heightDip(40f)
                            .backgroundColor(Background.color)
                    )
                    .alignItems(YogaAlign.CENTER)
                    .justifyContent(YogaJustify.CENTER)
            )
            .alignItems(YogaAlign.CENTER)
            .justifyContent(YogaJustify.CENTER)
            .child(
                Row.create(c)
                    .heightDip(30f)

                    .child(
                        Row.create(c)
                            .child(
                                Text.create(c)
                                    .text("Seconds: ")
                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                    .textColor(Green.color)
                                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
                            )
                            .child(
                                TextInput.create(c)
                                    .initialText(exercise.rest.toString())
                                    .textColorStateList(valueOf(Green.color))
                                    .textSizeSp(EXERCISE_TEXT_SIZE)
                                    .inputType(InputType.TYPE_CLASS_NUMBER)
                                    .inputBackgroundRes(0)
                                    .editable(editable)
                                    .hint("____")
                                    .hintColorStateList(valueOf(Green.color))
                                    .textChangedEventHandler(ExerciseItem.onChangeExerciseRest(c))
                                    .build()
                            )

                    )
                    .alignItems(YogaAlign.CENTER)

            )
            .justifyContent(YogaJustify.CENTER)
            .alignItems(YogaAlign.CENTER)
            .backgroundColor(CurrentLine.color)
            .build()
    }

    @OnEvent(TextChangedEvent::class)
    fun onChangeExerciseRest(c: ComponentContext, @FromEvent text: String, @State exercise: Exercise) {
        exercise.rest = parseInput(text, c)
        ExerciseItem.changeExercise(c, exercise)
    }

    @OnEvent(TextChangedEvent::class)
    fun onChangeExerciseWeight(c: ComponentContext, @FromEvent text: String, @State exercise: Exercise) {
        exercise.weight = parseInput(text, c)
        ExerciseItem.changeExercise(c, exercise)
    }

    @OnEvent(TextChangedEvent::class)
    fun onChangeExerciseRepetitions(c: ComponentContext, @FromEvent text: String, @State exercise: Exercise) {
        exercise.repetitions = parseInput(text, c)
        ExerciseItem.changeExercise(c, exercise)
    }

    @OnEvent(TextChangedEvent::class)
    fun onChangeExerciseName(c: ComponentContext, @FromEvent text: String, @State exercise: Exercise) {
        exercise.name = text
        ExerciseItem.changeExercise(c, exercise)
    }

    @OnEvent(TextChangedEvent::class)
    fun onChangeExerciseWorkUnitRest(c: ComponentContext, @FromEvent text: String, @State exercise: Exercise) {
        exercise.workUnit.rest = parseInput(text, c)
        ExerciseItem.changeExercise(c, exercise)
    }

    @OnEvent(TextChangedEvent::class)
    fun onChangeExerciseWorkUnitWork(c: ComponentContext, @FromEvent text: String, @State exercise: Exercise) {
        exercise.workUnit.work = parseInput(text, c)
        ExerciseItem.changeExercise(c, exercise)
    }

    private fun parseInput(text: String, c: ComponentContext): Int {
        var value = 0
        if (text.isNotEmpty()) {
            try {
                value = text.toInt()
            } catch (nfe: NumberFormatException) {
                Toast.makeText(c.androidContext, "'$text' is not a valid number.", Toast.LENGTH_SHORT).show()
            }
        }
        return value
    }

    @OnUpdateState
    fun changeExercise(exercise: StateValue<Exercise>, @Param value: Exercise) {
        exercise.set(value)
    }


}


