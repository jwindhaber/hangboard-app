package com.example.hangboard.components.workout

import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputType
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
import com.facebook.litho.widget.*
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import io.paperdb.Paper


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
                Row.create(c)
                    .widthDip(250f)
                    .heightDip(30f)
                    .child(
                        TextInput.create(c)
                            .initialText(workout.name)
                            .textColorStateList(ColorStateList.valueOf(Blue.color))
                            .textSizeSp(20f)
                            .inputType(InputType.TYPE_CLASS_NUMBER)
                            .inputBackgroundRes(0)
                            .editable(true)
//                            .inputFilter(ExerciseItemSpec.lenFilter)
//                            .hint(ExerciseItemSpec.HINT)
                            .hintColorStateList(ColorStateList.valueOf(Blue.color))
                            .textChangedEventHandler(ExpandableWorkoutItem.onChangeExerciseRepetitions(c))
                            .build()
                    )
            )
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
                            .clickHandler(ExpandableWorkoutItem.onClickAddActivity(c))
                    )
                    .child(
                        Text.create(c)
                            .paddingDip(YogaEdge.ALL, 10f)
                            .text("SAVE")
                            .textSizeSp(20f)
                            .flexGrow(1f)
                            .alignSelf(YogaAlign.CENTER)
                            .textAlignment(Layout.Alignment.ALIGN_CENTER)
                            .backgroundColor(Selection.color)
                            .textColor(Red.color)
                            .clickHandler(ExpandableWorkoutItem.onClickSaveWorkout(c))
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
    fun onClickSaveWorkout(c: ComponentContext, @State workout: Workout) {
       //TODO Extract to a Proper Repo layer
        Paper.book("workouts").write(workout.name, workout)
    }

    @OnEvent(ClickEvent::class)
    fun onClickAddActivity(c: ComponentContext, @State workout: Workout) {
        ExpandableWorkoutItem.onUpdateListSync(c)
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
    fun onUpdateList(workout: StateValue<Workout>) {

        //Null guard
        val initialWorkout = workout.get() ?: return

        val activitiesListToUpdate = ArrayList(initialWorkout.activities)
        val defaultWorkUnit = WorkUnit("defaultWorkUnit", 7, 3)

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
        initialWorkout.activities = activitiesListToUpdate
        workout.set(initialWorkout)
    }


    @OnEvent(TextChangedEvent::class)
    fun onChangeExerciseRepetitions(c: ComponentContext, @FromEvent text: String, @State workout: Workout) {
        workout.name = text
        ExpandableWorkoutItem.changeWorkout(c, workout)
    }

    @OnUpdateState
    fun changeWorkout(workout: StateValue<Workout>, @Param value: Workout) {
        workout.set(value)
    }

}


