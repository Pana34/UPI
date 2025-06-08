package com.example.cultura

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrijavaUnosPodatakaMediumTest {

    // Pravilo koje automatski pokreće MainActivity prije svakog testa
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Medium test: provjerava unos e-maila, lozinke i označavanje checkboxa ("Ostani prijavljen/a")
    @Test
    fun prijavaUnosPodatakaMediumTest() {
        // Simulacija unosa e-mail adrese
        onView(withHint(R.string.email_unos))
            .perform(typeText("korisnik@email.com"), closeSoftKeyboard())

        // Simulacija unosa zaporke
        onView(withHint(R.string.zaporka_unos))
            .perform(typeText("zaporka123"), closeSoftKeyboard())

        // Klik na checkbox "Ostani prijavljen/a"
        onView(withId(R.id.ostaniprijavljen)).perform(click())

        // Provjera da je checkbox označen
        onView(withId(R.id.ostaniprijavljen)).check(matches(isChecked()))
    }

}