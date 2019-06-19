package com.example.hangboard.components.events;

import com.facebook.litho.annotations.Event;

@Event
public class TimeLineChangedEvent {
    Integer color;
    String title;
    String subtitle;
}
