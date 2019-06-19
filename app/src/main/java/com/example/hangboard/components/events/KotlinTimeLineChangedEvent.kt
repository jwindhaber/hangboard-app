package com.example.hangboard.components.events

import com.facebook.litho.annotations.Event

@Event
class KotlinTimeLineChangedEvent {
    internal var color: Int? = null
    internal var title: String? = null
    internal var subtitle: String? = null
}