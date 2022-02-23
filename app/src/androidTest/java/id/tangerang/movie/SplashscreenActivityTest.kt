package id.tangerang.movie

import android.app.Application
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.google.android.material.tabs.TabLayout
import id.tangerang.movie.data.source.local.entity.ModelMovie
import id.tangerang.movie.utils.DataDummy
import id.tangerang.movie.utils.EspressoIdlingResource
import id.tangerang.movie.utils.Helpers
import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashscreenActivityTest : TestCase() {
    private lateinit var application: Application
    private lateinit var dummyMovie: MutableList<ModelMovie>
    private lateinit var dummyTv: MutableList<ModelMovie>

    @get:Rule
    var activityRule = ActivityScenarioRule(SplashscreenActivity::class.java)

//    @Rule
//    @JvmField
//    var activityRule = ActivityTestRule(SplashscreenActivity::class.java, true)

    @Before
    public override fun setUp() {
        super.setUp()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource())
        application = ApplicationProvider.getApplicationContext()
        dummyMovie = DataDummy.getDummyMovie(application)
        dummyTv = DataDummy.getDummyTv(application)
    }

    @After
    override fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource())
    }


    @Test
    fun loadMovie() {
        Thread.sleep(3000)
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        //loadDetailMovie
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(dummyMovie[3].title)))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(withText(Helpers.parseToDateMovie(dummyMovie[3].release_date))))
        onView(withId(R.id.tvLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(matches(withText("language ${dummyMovie[3].original_language}")))
        onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tvScore)).check(matches(withText("score user ${dummyMovie[3].vote_average}")))
        onView(withId(R.id.tvPopular)).check(matches(isDisplayed()))
        onView(withId(R.id.tvPopular)).check(matches(withText("popularity ${dummyMovie[3].popularity}")))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(dummyMovie[3].overview)))
        onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.ivPoster)).perform(click())
        Thread.sleep(1000)
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //loadDetailTV
        onView(withId(R.id.tab_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_layout)).perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        //detail tv

        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(dummyTv[1].title)))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(withText(Helpers.parseToDateMovie(dummyTv[1].release_date))))
        onView(withId(R.id.tvLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(matches(withText("language ${dummyTv[1].original_language}")))
        onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tvScore)).check(matches(withText("score user ${dummyTv[1].vote_average}")))
        onView(withId(R.id.tvPopular)).check(matches(isDisplayed()))
        onView(withId(R.id.tvPopular)).check(matches(withText("popularity ${dummyTv[1].popularity}")))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(dummyTv[1].overview)))
        onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.ivPoster)).perform(click())
        Thread.sleep(1000)
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //close
    }

    private fun selectTabAtPosition(indexTab1: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $indexTab1"

            override fun getConstraints() = CoreMatchers.allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(indexTab1)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $indexTab1"))
                        .build()
                tabAtIndex.select()
            }
        }
    }
}