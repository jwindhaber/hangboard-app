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

        val context = ComponentContext(this)


//        val createWorkoutItem = RecyclerCollectionComponent.create(context)
//            .disablePTR(true)
//            .section(WorkoutItemSection.create(SectionContext(context)).workout(WorkoutProvider.getWorkout()).build())
//            .build()
//        val beepList = RecyclerCollectionComponent.create(context)
//            .disablePTR(true)
//            .section(BeepList.create(SectionContext(context)).build())
//            .build()
//
//
//        val customBeep = CustomBeep.create(context).build()

        val createWorkoutItem = ExpandableWorkoutItem.create(context).initialWorkout(WorkoutProvider.getWorkout()).build()


        val timerComponent = TimerItem.create(context).timeline(WorkoutProvider.workoutToTimeline(WorkoutProvider.getWorkout())).build()

        val historyComponent = RecyclerCollectionComponent.create(context)
            .disablePTR(true)
            .section(HistoryListSection.create(SectionContext(context)).build())
            .build()


        val listener = object : HomeComponentSpec.HomeComponentClickListener {

            override fun onClickCreateWorkout() {
                root?.setComponentAsync(createWorkoutItem)
            }

            override fun onHangboardClick() {
                root?.setComponentAsync(timerComponent)
            }

            override fun onHistoryClick() {
                root?.setComponentAsync(historyComponent)
            }

        }

        homeComponent = HomeComponent.create(context).listener(listener).build()
        root = LithoView.create(this,  homeComponent)
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
