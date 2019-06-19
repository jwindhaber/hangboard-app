package com.example.hangboard.components.button

import android.R
import android.view.View
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Card
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaJustify


@LayoutSpec
object ButtonItemSpec {

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop text: String): Component {
        return Column.create(c)
            .alignItems(YogaAlign.CENTER)
            .justifyContent(YogaJustify.CENTER)
            .child(
                Card.create(c)
                    .cardBackgroundColorRes(R.color.darker_gray)
                    .cornerRadiusDip(2f)
                    .elevationDip(2f)
                    .content(ButtonTextItem.create(c).text(text))
                    .flexShrink(1f)
                    .alignSelf(YogaAlign.CENTER)
            )
            .clickHandler(ButtonItem.onClick(c))
            .build()
    }

    @OnEvent(ClickEvent::class)
    fun onClick(c: ComponentContext, @FromEvent view: View, @Prop listener: OnButtonClickListener) {
        listener.onButtonClick()
    }

    interface OnButtonClickListener {
        fun onButtonClick()
    }


}