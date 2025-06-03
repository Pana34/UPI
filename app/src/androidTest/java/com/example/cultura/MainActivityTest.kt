package com.example.cultura

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.init
import androidx.test.espresso.intent.Intents.release
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.*
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        init()
    }

    @After
    fun tearDown() {
        release()
    }

    @Test
    fun testLoginButtonOpensGlavnaStranica() {
        onView(withId(R.id.prijavagumb)).perform(click())
        intended(hasComponent(GlavnaStranica::class.java.name))
    }
}
