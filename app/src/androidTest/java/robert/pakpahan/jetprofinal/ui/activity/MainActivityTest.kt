package robert.pakpahan.jetprofinal.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.After
import org.junit.Test
import org.junit.Before
import robert.pakpahan.jetprofinal.R
import robert.pakpahan.jetprofinal.utils.DataDummy
import robert.pakpahan.jetprofinal.utils.EspressoIdlingResource

class MainActivityTest {

    private val dummyMovie = DataDummy.getMovies()
    private val dummyTvShow = DataDummy.getTvShows()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rv_movies)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.iv_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.iv_backdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.collapsing)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_genre_duration)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        Espresso.onView(withId(R.id.tvShowFragment)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_shows)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(withId(R.id.tvShowFragment)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_shows))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.iv_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.iv_backdrop))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.collapsing))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_genre_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadFavMovies() {
        Espresso.onView(withId(R.id.favoriteFragment)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_fav_movies)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_fav_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailFavMovie() {
        Espresso.onView(withId(R.id.rv_movies)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.fab_add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        Espresso.onView(withId(R.id.favoriteFragment)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_fav_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.iv_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.fab_add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.iv_backdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.collapsing)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_genre_duration)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadFavTvShows() {
        Espresso.onView(withId(R.id.favoriteFragment)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV SHOW")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_fav_tv_show)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_fav_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailFavTvShow() {
        Espresso.onView(withId(R.id.tvShowFragment)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_shows)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.fab_add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        Espresso.onView(withId(R.id.favoriteFragment)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV SHOW")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_fav_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.iv_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.fab_add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.iv_backdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.collapsing)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_genre_duration)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}