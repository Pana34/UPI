package com.example.cultura

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cultura.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.`is`
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun childAtPosition(
    parentMatcher: Matcher<View>, position: Int
): Matcher<View> {

    return object : BoundedMatcher<View, View>(View::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("Child at position $position in parent ")
            parentMatcher.describeTo(description)
        }

        override fun matchesSafely(view: View): Boolean {
            val parent = view.parent
            return parent is ViewGroup && parentMatcher.matches(parent)
                    && view == parent.getChildAt(position)
        }
    }
}
@RunWith(AndroidJUnit4::class)
class EndToEnd2 {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun endToEndRegisterLoginAndKvizTest() {
        onView(withId(R.id.register_link)).perform(click())
        Thread.sleep(1000)

        val testEmail = "testuser@example.com"
        val testPassword = "testpass123"

        onView(withId(R.id.register_email))
            .perform(typeText(testEmail), closeSoftKeyboard())

        onView(withId(R.id.register_password))
            .perform(typeText(testPassword), closeSoftKeyboard())

        onView(withId(R.id.register_confirm_password))
            .perform(typeText(testPassword), closeSoftKeyboard())

        onView(withId(R.id.register_button)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.login_email))
            .perform(typeText(testEmail), closeSoftKeyboard())

        onView(withId(R.id.login_password))
            .perform(typeText(testPassword), closeSoftKeyboard())

        onView(withId(R.id.prijavagumb)).perform(click())

        Thread.sleep(3000)

        onView(withId(R.id.slikaGrcka)).perform(click())

        Thread.sleep(1000)

        onView(withId(R.id.gumbKviz)).perform(click())

        Thread.sleep(1000)

        for (i in 1..4) {
            onView(childAtPosition(withId(R.id.opcijeGroup), 0)).perform(click())

            onView(withId(R.id.sljedeciButton)).perform(click())

            Thread.sleep(500)
        }

        onView(withText("Rezultat")).check(matches(isDisplayed()))
    }
}
