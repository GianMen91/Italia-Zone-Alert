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
import project.org.italiazonealert.R


@RunWith(AndroidJUnit4::class)
@LargeTest
class UsefulLinksActivityTest {
    @JvmField
    @Rule
    var linksUtilsActivityTestRules: ActivityTestRule<UsefulLinksActivity> = ActivityTestRule(
        UsefulLinksActivity::class.java
    )

    @Test
    fun testLinksTextViewIsDisplayedWithCorrectTextAndColor() {
        // Check that the "Links" TextView is displayed with the correct text and text color
        onView(withId(R.id.linksText))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.links)))
            .check(matches(hasTextColor(R.color.colorPrimary)))
    }

    @Test
    fun testMainTextIsDisplayedWithCorrectTextAndColor() {
        // Check that the "mainText" TextView is displayed with the correct text and text color
        onView(withId(R.id.mainText))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.linkUtiliText)))
            .check(matches(hasTextColor(android.R.color.black)))

    }
}