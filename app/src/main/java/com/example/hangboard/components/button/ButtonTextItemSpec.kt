package com.example.hangboard.components.button

import android.R
import android.text.Layout
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge


@LayoutSpec
object ButtonTextItemSpec {

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop text: String): Component {
        return Text.create(c)
            .text(text)
            .textSizeDip(18f)
            .textColorRes(R.color.white)
            .textSizeSp(18f)
            .textAlignment(Layout.Alignment.ALIGN_CENTER)
            .alignSelf(YogaAlign.STRETCH)
            .paddingDip(YogaEdge.ALL, 8f)
            .build()
    }
}