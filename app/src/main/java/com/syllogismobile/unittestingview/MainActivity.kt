package com.syllogismobile.unittestingview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customView.initialize()
        updateCustomView()
    }

    private fun updateCustomView() {
        val model = CustomViewModel(
            "A cat's jaw has only up and down motion; it does not have any lateral, side to side motion, like dogs and humans.",
            10
        )

        customView.update(model)
    }
}