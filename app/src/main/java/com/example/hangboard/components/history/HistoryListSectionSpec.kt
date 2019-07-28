package com.example.hangboard.components.history

import com.example.hangboard.components.style.TomorrowNightStyle.Background
import com.example.hangboard.components.style.TomorrowNightStyle.CurrentLine
import com.example.hangboard.persistence.repository.WorkoutTemplateRepository
import com.facebook.litho.annotations.Prop
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.common.SingleComponentSection
import java.util.*


@GroupSectionSpec
object HistoryListSectionSpec {

    @OnCreateChildren
    fun onCreateChildren(c: SectionContext, @Prop historyClickedListener: HistoryListItemSpec.HistoryListItemClickListener): Children {
        val builder = Children.create()

        val workoutKeys = WorkoutTemplateRepository.getAllWorkoutTemplateNames()

        workoutKeys.forEachIndexed { index, key ->
            builder.child(
                SingleComponentSection.create(c)
                    .key(key)
                    .component(
                        HistoryListItem.create(c)
                            .color(if (index % 2 == 0) CurrentLine.color else Background.color)
                            .title(key)
                            .subtitle(Date().toString())
                            .listener(historyClickedListener)
                            .build()
                    )
            )
        }
        return builder.build()
    }
}