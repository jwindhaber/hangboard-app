package com.example.hangboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.hangboard.components.HomeComponent
import com.example.hangboard.components.HomeComponentSpec
import com.example.hangboard.components.TimerItem
import com.example.hangboard.components.history.HistoryListSection
import com.example.hangboard.components.workout.ExpandableWorkoutItem
import com.example.hangboard.components.workout.WorkoutItemSection
import com.example.hangboard.timeline.Timeline
import com.example.hangboard.timeline.TimelineFragment
import com.example.hangboard.workout.definition.FragmentIdentifier.REST
import com.example.hangboard.workout.definition.FragmentIdentifier.WORK
import com.example.hangboard.workout.util.WorkoutProvider
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.widget.RecyclerCollectionComponent


class MainActivity : AppCompatActivity() {


    private var root: LithoView? = null
    private var homeComponent: HomeComponent? = null

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val timeline = Timeline(
            listOf(
                TimelineFragment(7, WORK),
                TimelineFragment(3, REST),
                TimelineFragment(7, WORK),
                TimelineFragment(3, REST),
                TimelineFragment(7, WORK),
                TimelineFragment(180, REST)
            )
        )


        val context = ComponentContext(this)


        val timerComponent = TimerItem.create(context).timeline(timeline).build()


        val historyComponent = RecyclerCollectionComponent.create(context)
            .disablePTR(true)
            .section(HistoryListSection.create(SectionContext(context)).build())
            .build()

        val listener = object : HomeComponentSpec.HomeComponentClickListener {
            override fun onHangboardClick() {
                root?.setComponentAsync(timerComponent)
            }

            override fun onHistoryClick() {
                root?.setComponentAsync(historyComponent)
            }
        }



        val workoutItem = RecyclerCollectionComponent.create(context)
            .disablePTR(true)
            .section(WorkoutItemSection.create(SectionContext(context)).workout(WorkoutProvider.getWorkout()).build())
            .build()

        val expandableWorkoutItem = ExpandableWorkoutItem.create(context).initialWorkout(WorkoutProvider.getWorkout()).build()

        homeComponent = HomeComponent.create(context).listener(listener).build()
//        root = LithoView.create(this, homeComponent)
        root = LithoView.create(this,  expandableWorkoutItem)


        setContentView(root)
    }


    override fun onBackPressed() {
        root?.setComponentAsync(homeComponent)
//        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
// Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
