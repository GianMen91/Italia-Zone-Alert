package project.org

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import project.org.italiazonealert.MainActivity


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @JvmField
    @Rule
    var mainActivityTestRules: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )




}