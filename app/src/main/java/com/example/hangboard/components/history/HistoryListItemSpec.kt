package com.example.hangboard.components.history

import android.util.Log
import android.view.View
import com.example.hangboard.components.style.TomorrowNightStyle.Blue
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge


@LayoutSpec
object HistoryListItemSpec {

    private const val TAG = "HistoryListItem"

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop color: Int, @Prop title: String, @Prop subtitle: String): Component {

        return Column.create(c)
            .paddingDip(YogaEdge.ALL, 16f)
            .backgroundColor(color)
            .clickHandler(HistoryListItem.onClickHistory(c, title))
            .child(
                Text.create(c)
                    .text(title)
                    .textSizeSp(40f)
                    .textColor(Blue.color)
            )
            .child(
                Text.create(c)
                    .text(subtitle)
                    .textSizeSp(20f)
                    .textColor(Blue.color)
            )
            .build()
    }


    @OnEvent(ClickEvent::class)
    fun onClickHistory(c: ComponentContext, @FromEvent view: View, @Prop listener: HistoryListItemClickListener, @Param workoutId: String) {
        Log.i(TAG, "History clicked")
        listener.onHistoryListItemClick(workoutId)
    }


    interface HistoryListItemClickListener {
        fun onHistoryListItemClick(workoutId: String)
    }

}