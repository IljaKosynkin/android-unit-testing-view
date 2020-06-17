package com.syllogismobile.unittestingview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.custom_view.view.*

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.custom_view, this)
    }

    fun initialize() {
        orientation = VERTICAL

        likeButton.setOnClickListener {
            likes.text = (likes.text.toString().toInt() + 1).toString()
        }
    }

    fun update(model: CustomViewModel) {
        fact.text = model.text
        likes.text = model.numberOfLikes.toString()
    }
}