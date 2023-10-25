package project.org

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import project.org.italiazonealert.R
import project.org.italiazonealert.ZoneActivity


@RunWith(AndroidJUnit4::class)
@LargeTest
class ZoneActivityTest {
    @JvmField
    @Rule
    var zoneActivityTestRules: ActivityTestRule<ZoneActivity> = ActivityTestRule(
        ZoneActivity::class.java
    )

    @Test
    fun testYellowZoneButtonIsDisplayedWithCorrectTextAndBackgroundColor() {
        // Check that the "Yellow Zone" Button is displayed with the correct text and background color
        onView(withId(R.id.button_yellow_zone))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.zona_gialla)))

        val bar = zoneActivityTestRules.activity.findViewById<View>(R.id.button_yellow_zone)
        val actualColor = (bar.background as ColorDrawable).color
        val expectedColor = Color.parseColor("#FFEB3B")
        assertEquals(actualColor, expectedColor)
    }

    @Test
    fun testOrangeZoneButtonIsDisplayedWithCorrectTextAndBackgroundColor() {
        // Check that the "Orange Zone" Button is displayed with the correct text and background color
        onView(withId(R.id.button_orange_zone))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.zona_arancione)))

        val bar = zoneActivityTestRules.activity.findViewById<View>(R.id.button_orange_zone)
        val actualColor = (bar.background as ColorDrawable).color
        val expectedColor = Color.parseColor("#FF9800")
        assertEquals(actualColor, expectedColor)
    }

    @Test
    fun testRedZoneButtonIsDisplayedWithCorrectTextAndBackgroundColor() {
        // Check that the "Red Zone" Button is displayed with the correct text and background color
        onView(withId(R.id.button_red_zone))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.zona_rossa)))

        val bar = zoneActivityTestRules.activity.findViewById<View>(R.id.button_red_zone)
        val actualColor = (bar.background as ColorDrawable).color
        val expectedColor = Color.parseColor("#E91E63")
        assertEquals(actualColor, expectedColor)
    }

    @Test
    fun testZoneTextViewIsDisplayedWithCorrectTextAndTextColor() {
        // Check that the "zone" TextView is displayed with the correct text and text color
        onView(withId(R.id.zone))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.zona_gialla_oneline)))

        val bar = zoneActivityTestRules.activity.findViewById<View>(R.id.button_yellow_zone)
        val actualColor = (bar.background as ColorDrawable).color
        val expectedColor = Color.parseColor("#FFEB3B")
        assertEquals(actualColor, expectedColor)
    }

    @Test
    fun testNoteTextViewIsDisplayedWithCorrectTextAndTextColor() {
        // Check that the "note" TextView is displayed with the correct text and text color
        onView(withId(R.id.note))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.important_note)))
            .check(matches(hasTextColor(android.R.color.black)))
    }

    @Test
    fun testZoneDescriptionTextViewIsDisplayedWithCorrectTextAndTextColor() {
        // Check that the "zone_description" TextView is displayed with the correct text and text color
        onView(withId(R.id.zone_description))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.zona_gialla_text)))
            .check(matches(hasTextColor(android.R.color.black)))
    }
}