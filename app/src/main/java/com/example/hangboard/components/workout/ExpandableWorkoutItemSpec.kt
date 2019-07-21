package com.example.hangboard.components.workout

import android.graphics.Color
import android.text.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.hangboard.components.style.TomorrowNightStyle.*
import com.example.hangboard.components.workout.events.DeleteActivityEvent
import com.example.hangboard.workout.dto.Activity
import com.example.hangboard.workout.dto.Exercise
import com.example.hangboard.workout.dto.WorkUnit
import com.example.hangboard.workout.dto.Workout
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.common.DataDiffSection
import com.facebook.litho.sections.common.RenderEvent
import com.facebook.litho.sections.widget.NotAnimatedItemAnimator
import com.facebook.litho.sections.widget.RecyclerCollectionComponent
import com.facebook.litho.widget.ComponentRenderInfo
import com.facebook.litho.widget.RenderInfo
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge


@LayoutSpec
object ExpandableWorkoutItemSpec {

    private const val TAG = "ExpandableWorkoutItem"

    @OnCreateInitialState
    fun onCreateInitialState(c: ComponentContext, workout: StateValue<Workout>, @Prop initialWorkout: Workout) {
        workout.set(initialWorkout)
    }


    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @State workout: Workout): Component {
        return Column.create(c)
            .child(
                RecyclerCollectionComponent.create(c)
                    .flexGrow(1f)
                    .disablePTR(true)
                    .itemAnimator(NotAnimatedItemAnimator() as RecyclerView.ItemAnimator?)
                    .section(
                        DataDiffSection.create<Activity>(SectionContext(c))
                            .data(workout.activities)
                            .renderEventHandler(ExpandableWorkoutItem.onRender(c))
                    )
                    .paddingDip(YogaEdge.TOP, 8f)
                )
            .child(
                Row.create(c)
                    .backgroundColor(Color.LTGRAY)
                    .child(
                        Text.create(c)
                            .paddingDip(YogaEdge.ALL, 10f)
                            .text("ADD")
                            .textSizeSp(20f)
                            .flexGrow(1f)
                            .alignSelf(YogaAlign.CENTER)
                            .testKey("ADD")
                            .textAlignment(Layout.Alignment.ALIGN_CENTER)
                            .backgroundColor(Selection.color)
                            .textColor(Green.color)
                            .clickHandler(ExpandableWorkoutItem.onClick(c, true))
                    )
                    .child(
                        Text.create(c)
                            .paddingDip(YogaEdge.ALL, 10f)
                            .text("REMOVE")
                            .textSizeSp(20f)
                            .flexGrow(1f)
                            .alignSelf(YogaAlign.CENTER)
                            .textAlignment(Layout.Alignment.ALIGN_CENTER)
                            .backgroundColor(Selection.color)
                            .textColor(Red.color)
                            .clickHandler(ExpandableWorkoutItem.onClick(c, false))
                    )
            )

            .backgroundColor(Background.color)
            .build()
    }

    @OnEvent(RenderEvent::class)
    fun onRender(c: ComponentContext, @FromEvent model: Activity): RenderInfo {

        val hasEventDispatcher = object : HasEventDispatcher {
            override fun getEventDispatcher(): EventDispatcher {

                return object : EventDispatcher {
                    override fun dispatchOnEvent(eventHandler: EventHandler<*>?, eventState: Any?): Any? {
                        val event = eventState as DeleteActivityEvent
                        ExpandableWorkoutItem.removeActivityFromListSync(c, event.activityId)
                        return null
                    }

                }
            }

        }

        val deleteActivityHandler = EventHandler<DeleteActivityEvent>(hasEventDispatcher, 1234, null)


        return ComponentRenderInfo.create()
            .component(
                ActivityItem.create(c)
                    .activity(model)
                    .deleteActivityEventHandler(deleteActivityHandler)
                    .build()
            )
            .build()
    }

    @OnEvent(ClickEvent::class)
    fun onClick(c: ComponentContext, @State workout: Workout, @Param adding: Boolean) {
        ExpandableWorkoutItem.onUpdateListSync(c, adding)
    }

    @OnUpdateState
    fun removeActivityFromList(workout: StateValue<Workout>, @Param activityId: String) {
        //Null guard
        val initialWorkout = workout.get() ?: return

        val activitiesListToUpdate = ArrayList(initialWorkout.activities)
        activitiesListToUpdate.removeAll { it.activityId == activityId }


        initialWorkout.activities = activitiesListToUpdate
        workout.set(initialWorkout)
    }



    @OnUpdateState
    fun onUpdateList(workout: StateValue<Workout>, @Param adding: Boolean) {

        //Null guard
        val initialWorkout = workout.get() ?: return

        val activitiesListToUpdate = ArrayList(initialWorkout.activities)
        val defaultWorkUnit = WorkUnit("defaultWorkUnit", 7, 3)

        if (adding) {
            activitiesListToUpdate.add(
                Activity(
                    name = "bla",
                    rest = 240,
                    exercises = listOf(
                        Exercise("first", 180, 7, 35, defaultWorkUnit),
                        Exercise("first", 180, 6, 35, defaultWorkUnit),
                        Exercise("first", 180, 5, 35, defaultWorkUnit)
                    )
                )
            )
        } else {
            if(activitiesListToUpdate.size > 0){
                activitiesListToUpdate.removeAt(activitiesListToUpdate.size-1)
            }
        }
        initialWorkout.activities = activitiesListToUpdate
        workout.set(initialWorkout)
    }

}


