package com.example.hangboard.components

import android.text.Layout
import android.util.Log
import android.view.View
import com.example.hangboard.components.style.TomorrowNightStyle
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import java.lang.Float.NaN


@LayoutSpec
object HomeComponentSpec {

    private const val TAG = "HomeComponent"

    @OnCreateLayout
//    fun onCreateLayout(c: ComponentContext, @Prop hint: String, @Prop binder: RecyclerBinder): Component {
    fun onCreateLayout(c: ComponentContext): Component {
        return Column.create(c)
            .child(
                Row.create(c)
                    .widthDip(350f)
                    .heightDip(70f)
                    .alignItems(YogaAlign.CENTER)
//                    .alignContent(YogaAlign.FLEX_START)
                    .positionDip(YogaEdge.ALL, NaN)
                    .backgroundColor(TomorrowNightStyle.Selection.color)
                    .marginDip(YogaEdge.BOTTOM, 15f)
                    .clickHandler(HomeComponent.onClickHangboard(c))
                    .child(
                        Text.create(c)
                            .text("Hangboard Training")
                            .textSizeDip(24f)
                            .textColor(TomorrowNightStyle.Blue.color)
                            .textSizeSp(24f)
                            .textAlignment(Layout.Alignment.ALIGN_CENTER)
                            .alignSelf(YogaAlign.STRETCH)
                            .paddingDip(YogaEdge.ALL, 8f)
                    )
            )
            .child(
                Row.create(c)
                    .widthDip(350f)
                    .heightDip(70f)
                    .alignItems(YogaAlign.CENTER)
//                    .alignContent(YogaAlign.FLEX_START)
                    .positionDip(YogaEdge.ALL, NaN)
                    .backgroundColor(TomorrowNightStyle.Selection.color)
                    .marginDip(YogaEdge.BOTTOM, 15f)
                    .clickHandler(HomeComponent.onClickHistory(c))
                    .child(
                        Text.create(c)
                            .text("History")
                            .textSizeDip(24f)
                            .textColor(TomorrowNightStyle.Blue.color)
                            .textSizeSp(24f)
                            .textAlignment(Layout.Alignment.ALIGN_CENTER)
                            .alignSelf(YogaAlign.STRETCH)
                            .paddingDip(YogaEdge.ALL, 8f)
                    )
            )
            .child(
                Row.create(c)
                    .widthDip(400f)
                    .heightDip(100f)
                    .positionDip(YogaEdge.ALL, NaN)
            )
            .widthDip(500f)
            .heightDip(600f)
            .alignItems(YogaAlign.CENTER)
            .alignContent(YogaAlign.FLEX_START)
            .paddingDip(YogaEdge.VERTICAL, 100f)
            .positionDip(YogaEdge.ALL, NaN)
            .backgroundColor(TomorrowNightStyle.Background.color)
            .build()
    }

    @OnEvent(ClickEvent::class)
    fun onClickHangboard(c: ComponentContext, @FromEvent view: View, @Prop listener: HomeComponentClickListener) {
        Log.i(TAG, "Hangboard clicked")
        listener.onHangboardClick()
    }

    @OnEvent(ClickEvent::class)
    fun onClickHistory(c: ComponentContext, @FromEvent view: View, @Prop listener: HomeComponentClickListener) {
        Log.i(TAG, "History clicked")
        listener.onHistoryClick()
    }


    interface HomeComponentClickListener {
        fun onHangboardClick()
        fun onHistoryClick()
    }





}