package com.syllogismobile.unittestingview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.android.synthetic.main.custom_view.view.*
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class CustomViewUnitTests {
    @MockK private lateinit var context: Context
    @MockK private lateinit var layoutInflater: LayoutInflater

    @MockK private lateinit var fact: TextView
    @MockK private lateinit var likes: TextView
    @MockK private lateinit var likeButton: Button

    private lateinit var subject: CustomView

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        every { LayoutInflater.from(context) } returns layoutInflater
        every { layoutInflater.inflate(any<Int>(), any()) } returns mockk()

        subject = spyk(CustomView(context))

        every { subject.context } returns context
        every { subject.orientation = any() } returns Unit
        every { subject.fact } returns fact
        every { subject.likes } returns likes
        every { subject.likeButton } returns likeButton

        subject.initialize()
    }

    @Test
    fun testInit() {
        every { likes.text } returns "1"

        val clickSlot = slot<View.OnClickListener>()
        verify(exactly = 1) {
            likeButton.setOnClickListener(capture(clickSlot))
            subject.orientation = VERTICAL
        }

        clickSlot.captured.onClick(likeButton)

        verify(exactly = 1) { likes.text = "2" }
    }

    @Test
    fun testUpdatesCorrectly() {
        val mockModel = CustomViewModel("text", 10)

        subject.update(mockModel)

        verify(exactly = 1) {
            fact.text = "text"
            likes.text = "10"
        }
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUpClass() {
            mockkStatic(LayoutInflater::class)
        }

        @JvmStatic
        @AfterClass
        fun tearDownClass() {
            unmockkStatic(LayoutInflater::class)
        }
    }
}