package project.org

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import project.org.italiazonealert.HomeActivity
import project.org.italiazonealert.R


@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {
    @JvmField
    @Rule
    var homeActivityTestRules: ActivityTestRule<HomeActivity> = ActivityTestRule(
        HomeActivity::class.java
    )

    @Test
    fun testItalianMapImageViewIsDisplayed() {
        // Check that the Italian map ImageView is displayed
        onView(withId(R.id.italianMap)).check(matches(isDisplayed()))
    }

    @Test
    fun testLogoImageViewIsDisplayed() {
        // Check that the logo ImageView is displayed
        onView(withId(R.id.logo)).check(matches(isDisplayed()))
    }

    @Test
    fun testCopyrightTextIsDisplayedWithCorrectTextAndColor() {
        // Check that the copyright text is displayed
        onView(withId(R.id.copyrightText))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.copyright))) // Replace with the appropriate string resource
            .check(matches(hasTextColor(android.R.color.white))) // Replace with the appropriate color resource
    }

}