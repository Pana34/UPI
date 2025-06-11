package com.example.cultura

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.Espresso.onData
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EndToEndTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun korisnickiTokPromjenaJezikaTest() {
        onView(withId(R.id.prijavagumb)).perform(click())
        onView(allOf(withId(R.id.nav_postavke), isDisplayed())).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.spinnerJezik)).perform(click())
        onData(anything()).atPosition(1).perform(click())
        onView(withId(R.id.spinnerJezik)).check(matches(notNullValue()))
    }
}