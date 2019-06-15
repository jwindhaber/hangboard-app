package com.example.hangboard

import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.hangboard.timeline.Timeline
import com.example.hangboard.timeline.TimelineFragment
import com.example.hangboard.timer.HangboardTimer
import com.example.hangboard.workout.definition.FragmentIdentifier
import com.example.hangboard.workout.definition.FragmentIdentifier.*

import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration
import java.time.Duration.ofSeconds

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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

        val hangboardTimer = HangboardTimer(
            timeline = timeline,
            timerText = findViewById(R.id.timer_text),
            timerInfo = findViewById(R.id.timer_info),
            startButton = findViewById(R.id.start_button))

        hangboardTimer.init()


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
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
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
