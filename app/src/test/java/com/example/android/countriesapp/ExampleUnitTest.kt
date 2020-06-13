package com.example.android.countriesapp

import com.android.countriesapp.tools.tests.LogTestRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ExampleUnitTest {

    @get:Rule
    val logTestRule = LogTestRule()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}