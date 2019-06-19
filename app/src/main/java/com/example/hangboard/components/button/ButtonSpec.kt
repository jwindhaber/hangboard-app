package com.example.hangboard.components.button

import android.content.Context
import android.view.View
import android.widget.Button
import com.facebook.litho.ClickEvent
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.*


@MountSpec
object ButtonSpec {

    @OnCreateMountContent
    fun onCreateMountContent(context: Context): Button {
        return Button(context)
    }

    @OnMount
    fun onMount(context: ComponentContext, button: Button) {
        button.setText("Counter")
    }

    @OnEvent(ClickEvent::class)
    fun onClick(c: ComponentContext, @FromEvent view: View, @Prop listener: OnButtonClickListener) {
        listener.onButtonClick()
    }

    interface OnButtonClickListener {
        fun onButtonClick()
    }
}