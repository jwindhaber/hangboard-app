package com.example.hangboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.hangboard.components.HomeComponent
import com.example.hangboard.components.HomeComponentSpec
import com.example.hangboard.components.history.HistoryListItemSpec
import com.example.hangboard.components.history.HistoryListSection
import com.example.hangboard.components.timer.TimerItem
import com.example.hangboard.components.workout.ExpandableWorkoutItem
import com.example.hangboard.dto.Workout
import com.example.hangboard.timer.HangboardTimer
import com.example.hangboard.workout.util.WorkoutProvider
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.widget.RecyclerCollectionComponent
import io.paperdb.Paper


class MainActivity : AppCompatActivity() {


    private var root: LithoView? = null
    private var homeComponent: HomeComponent? = null

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = ComponentContext(this)


        val historyClickedListener = object : HistoryListItemSpec.HistoryListItemClickListener {
            override fun onHistoryListItemClick(workoutId: String) {
                //TODO extract to proper repo layer
                val workout = Paper.book("workouts").read<Workout>(workoutId)
                val createWorkoutItem = ExpandableWorkoutItem.create(context).initialWorkout(workout).build()
                root?.setComponentAsync(createWorkoutItem)
            }
        }



        val createWorkoutItem = ExpandableWorkoutItem.create(context).initialWorkout(WorkoutProvider.getWorkout()).build()


        val timerComponent = TimerItem.create(context).hangboardTimer(HangboardTimer(WorkoutProvider.getWorkout())).build()

        val historyComponent = RecyclerCollectionComponent.create(context)
            .disablePTR(true)
            .section(HistoryListSection.create(SectionContext(context)).historyClickedListener(historyClickedListener).build())
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
        root = LithoView.create(this,  createWorkoutItem)
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
