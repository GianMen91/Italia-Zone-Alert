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
import project.org.italiazonealert.NoConnectionActivity
import project.org.italiazonealert.R


@RunWith(AndroidJUnit4::class)
@LargeTest
class NoConnectionActivityTest {
    @JvmField
    @Rule
    var noConnectionActivityTestRules: ActivityTestRule<NoConnectionActivity> = ActivityTestRule(
        NoConnectionActivity::class.java
    )

    @Test
    fun testErrorMessageTextViewIsDisplayedWithCorrectTextAndColor() {
        // Check that the "errorMessage" TextView is displayed with the correct text and text color
        onView(withId(R.id.errorMessage))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.connection_error)))
    }


    @Test
    fun testRetryButtonIsDisplayedWithCorrectTextAndColor() {
        // Check that the "retry" Button is displayed with the correct text and text color
        onView(withId(R.id.retry))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.retry)))
            .check(matches(hasTextColor(R.color.white)))
    }

    @Test
    fun testRetryButtonHasBackgroundColorEqualToColorPrimary() {
        val bar = noConnectionActivityTestRules.activity.findViewById<View>(R.id.retry)
        val actualColor = (bar.background as ColorDrawable).color
        val expectedColor = Color.parseColor("#0F8C66")
        assertEquals(actualColor, expectedColor)
    }

}