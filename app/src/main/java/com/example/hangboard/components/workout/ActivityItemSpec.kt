package com.example.hangboard.components.workout

import android.R.attr.maxLength
import android.text.InputFilter
import android.text.InputType
import com.example.hangboard.components.style.TomorrowNightStyle.Background
import com.example.hangboard.components.style.TomorrowNightStyle.Foreground
import com.example.hangboard.components.workout.events.DeleteActivityEvent
import com.example.hangboard.persistence.dto.Activity
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.TextInput
import com.facebook.yoga.YogaAlign


@LayoutSpec(events = [DeleteActivityEvent::class])
object ActivityItemSpec {

    private const val TAG = "ActivityItemSpec"

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop activity: Activity): Component {
        val lenFilter = InputFilter.LengthFilter(maxLength)



        var builder = Column.create(c)
            .child(
                Row.create(c)
                    .widthDip(420f)
                    .heightDip(30f)
                    .backgroundColor(Foreground.color)
                    .alignSelf(YogaAlign.FLEX_START)
                    .child(
                        Row.create(c)
                            .widthDip(250f)
                            .heightDip(30f)
                            .child(
                                TextInput.create(c)
                                    .widthPercent(90f)
                                    .initialText("${activity.name}:")
                                    .textSizeSp(20f)
                                    .inputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                                    .inputBackgroundRes(0)
                                    .editable(false)
                                    .build()
                            )
                    )
                    .child(
                        Row.create(c)
                            .widthDip(130f)
                            .heightDip(30f)
                            .child(
                                Text.create(c)
                                    .text("DELETE")
                                    .textSizeSp(20f)
                                    .textColor(Background.color)
                                    .clickHandler(ActivityItem.onDeleteActivityClicked(c, activity.activityId))
                            )
                    )
                    .child(
                        Row.create(c)
                            .widthDip(40f)
                            .heightDip(30f)
                            .child(
                                Text.create(c)
                                    .text("ᴧ")
                                    .textSizeSp(20f)
                                    .textColor(Background.color)
                            )
                    )
                    .child(
                        Row.create(c)
                            .widthDip(40f)
                            .heightDip(30f)
                            .child(
                                Text.create(c)
                                    .text("v")
                                    .textSizeSp(20f)
                                    .textColor(Background.color)
                            )
                    )

            )


        activity.exercises.forEachIndexed { index, exercise ->
            builder.child(ExerciseItem.create(c).initialExercise(exercise).editable(true).setNumber(index + 1))
        }


        builder.alignItems(YogaAlign.CENTER)

        return builder.build()
    }


    @OnEvent(ClickEvent::class)
    fun onDeleteActivityClicked(c: ComponentContext, @Param activityId: String) {
        ActivityItem.dispatchDeleteActivityEvent(ActivityItem.getDeleteActivityEventHandler(c),activityId)
    }
}


