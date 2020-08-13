package io.github.occultus73.tictactoe

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import io.github.occultus73.tictactoe.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    lateinit var device: UiDevice

    @Before
    fun setup(){
        device = UiDevice.getInstance(getInstrumentation())
    }

    @Test
    fun testGameBoard_inView() {
        Espresso.onView(withId(R.id.game_board)).perform(click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        device.setOrientationLeft()
        Espresso.onView(withId(R.id.game_board)).perform(click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        device.setOrientationLeft()
        Espresso.onView(withId(R.id.game_board)).perform(click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        device.setOrientationLeft()
        Espresso.onView(withId(R.id.game_board)).perform(click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        device.setOrientationLeft()
        Espresso.onView(withId(R.id.game_board)).perform(click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}