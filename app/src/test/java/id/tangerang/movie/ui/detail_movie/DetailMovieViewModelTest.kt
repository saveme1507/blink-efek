package id.tangerang.movie.ui.detail_movie

import android.app.Application
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import id.tangerang.movie.data.source.local.entity.ModelMovie
import id.tangerang.movie.utils.DataDummy
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.Q])
class DetailMovieViewModelTest : TestCase() {
    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var application: Application
    private lateinit var dummy: MutableList<ModelMovie>

    @Before
    fun setUpData() {
        application = ApplicationProvider.getApplicationContext()
        viewModel = DetailMovieViewModel(application)
        dummy = DataDummy.getDummyMovie(application)
        viewModel.setMovie(dummy[0])
    }

    @Test
    fun testGetMovie() {
        val movieEntities = viewModel.getMovie()
        assertNotNull(movieEntities)
        assertEquals(movieEntities?.backdrop_path, dummy[0].backdrop_path)
        assertEquals(movieEntities?.genre_ids, dummy[0].genre_ids)
        assertEquals(movieEntities?.id, dummy[0].id)
        assertEquals(movieEntities?.original_language, dummy[0].original_language)
        assertEquals(movieEntities?.original_title, dummy[0].original_title)
        assertEquals(movieEntities?.overview, dummy[0].overview)
        assertEquals(movieEntities?.popularity, dummy[0].popularity)
        assertEquals(movieEntities?.poster_path, dummy[0].poster_path)
        assertEquals(movieEntities?.release_date, dummy[0].release_date)
        assertEquals(movieEntities?.title, dummy[0].title)
        assertEquals(movieEntities?.vote_average, dummy[0].vote_average)
        assertEquals(movieEntities?.vote_count, dummy[0].vote_count)
    }
}