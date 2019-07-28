package com.example.hangboard.components.workout

import com.example.hangboard.persistence.dto.Activity
import com.example.hangboard.persistence.dto.Workout
import com.facebook.litho.annotations.FromEvent
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.common.DataDiffSection
import com.facebook.litho.sections.common.RenderEvent
import com.facebook.litho.widget.ComponentRenderInfo
import com.facebook.litho.widget.RenderInfo


@GroupSectionSpec
object WorkoutItemSectionSpec {

    private const val TAG = "WorkoutItemSectionSpec"

    @OnCreateChildren
    fun onCreateChildren(c: SectionContext, @Prop workout: Workout): Children {

        return Children.create()
            .child(
                DataDiffSection.create<Activity>(c)
                    .data(workout.activities)
                    .renderEventHandler(WorkoutItemSection.onRender(c))
            )
            .build()
    }

    @OnEvent(RenderEvent::class)
    fun onRender(c: SectionContext, @FromEvent model: Activity): RenderInfo {
        return ComponentRenderInfo.create()
            .component(
                ActivityItem.create(c)
//                    .activity(model)
                    .build()
            )
            .build()
    }

}