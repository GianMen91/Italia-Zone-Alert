package project.org

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasLinks
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import project.org.italiazonealert.InfoActivity
import project.org.italiazonealert.R


@RunWith(AndroidJUnit4::class)
@LargeTest
class InfoActivityTest {
    @JvmField
    @Rule
    var infoActivityTestRules: ActivityTestRule<InfoActivity> = ActivityTestRule(
        InfoActivity::class.java
    )

    @Test
    fun versionIsDisplayed() {
        onView(withId(R.id.appVersion)).perform(scrollTo()).check(matches(isDisplayed()))
    }

    @Test
    fun infoTitleIsDisplayed() {
        onView(withId(R.id.infoTitle))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.infoTitle)))
    }

    @Test
    fun infoTitleNotDisplayedWrongText() {
        onView(withId(R.id.infoTitle))
            .check(matches((isDisplayed())))
            .check(matches(not(withText(R.string.information))))
    }


    @Test
    fun mainTextIsDisplayed() {
        onView(withId(R.id.mainText))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.information)))
    }

    @Test
    fun infoTitleColorIsColorPrimary() {
        onView(withId(R.id.infoTitle))
            .check(matches(hasTextColor(R.color.colorPrimary)))
    }

    @Test
    fun infoTitleColorIsNotAnotherColorDifferentThanColorPrimary() {
        onView(withId(R.id.infoTitle))
            .check(matches(not(hasTextColor(R.color.white))))
    }


    @Test
    fun mainTextHasClickableLinks() {
        onView(withId(R.id.mainText))
            .check(matches(hasLinks()))
    }

    @Test
    fun mainTextDoesNotHaveNotClickableLinks() {
        onView(withId(R.id.mainText))
            .check(matches(not(not(hasLinks()))))
    }


}