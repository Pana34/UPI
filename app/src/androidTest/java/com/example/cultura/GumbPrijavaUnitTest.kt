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
class GumbPrijavaUnitTest {

    // Pravilo koje automatski pokreće MainActivity prije svakog testa
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    //Metoda za inicijalizaciju praćenja Intenta (prije svakog testa)
    @Before
    fun priprema() {
        init()
    }

    //Metoda za oslobađanje resursa povezanih s Intentom (nakon svakog testa)
    @After
    fun reset() {
        release()
    }

    // Test provjerava da klik na gumb 'prijavagumb' pokreće aktivnost GlavnaStranica
    @Test
    fun glavnaStranicaUnitTest() {
        // Simulacija klika na gumb za prijavu
        onView(withId(R.id.prijavagumb)).perform(click())
        intended(hasComponent(GlavnaStranica::class.java.name))
    }
}
