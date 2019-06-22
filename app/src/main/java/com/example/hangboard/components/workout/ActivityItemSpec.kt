package com.example.hangboard.components.workout

import com.example.hangboard.components.style.TomorrowNightStyle.Background
import com.example.hangboard.components.style.TomorrowNightStyle.Foreground
import com.example.hangboard.workout.dto.Activity
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge


@LayoutSpec
object ActivityItemSpec {

    private const val TAG = "ActivityItemSpec"

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop activity: Activity): Component {
        var builder = Column.create(c)
            .child(
                Row.create(c)
                    .widthDip(420f)
                    .heightDip(30f)
                    .backgroundColor(Foreground.color)
                    .alignSelf(YogaAlign.FLEX_START)
                    .child(
                        Row.create(c)
                            .widthDip(340f)
                            .heightDip(30f)
                            .child(
                                Text.create(c)
                                    .paddingDip(YogaEdge.LEFT, 3f)
                                    .text("${activity.name}:")
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
            builder.child(ExerciseItem.create(c).exercise(exercise).setNumber(index + 1))
        }


        builder.alignItems(YogaAlign.CENTER)

        return builder.build()
    }

}


