package com.example.hangboard.components.history

import android.util.Log
import com.example.hangboard.components.style.TomorrowNightStyle.Background
import com.example.hangboard.components.style.TomorrowNightStyle.CurrentLine
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.common.SingleComponentSection
import java.util.*


@GroupSectionSpec
object HistoryListSectionSpec {

    @OnCreateChildren
    fun onCreateChildren(c: SectionContext): Children {
        val builder = Children.create()

        for (i in 0..31) {
            builder.child(
                SingleComponentSection.create(c)
                    .key(i.toString())
                    .component(
                        HistoryListItem.create(c)
                            .color(if (i % 2 == 0) CurrentLine.color else Background.color)
                            .title("$i. Hangboard")
                            .subtitle(Date().toString())
                            .listener(object : HistoryListItemSpec.HistoryListItemClickListener {
                                override fun onHistoryListItemClick() {
                                    Log.i("History", "History Clicked")
                                }

                            })
                            .build()
                    )
            )
        }
        return builder.build()
    }
}