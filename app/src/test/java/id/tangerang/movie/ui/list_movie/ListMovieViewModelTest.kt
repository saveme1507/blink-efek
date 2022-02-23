package id.tangerang.movie.ui.list_movie

import android.app.Application
import android.os.Build.VERSION_CODES.Q
import androidx.test.core.app.ApplicationProvider
import id.tangerang.movie.data.source.local.entity.ModelMovie
import id.tangerang.movie.utils.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Q])
class ListMovieViewModelTest {
    private lateinit var viewModel: ListMovieViewModel
    private lateinit var application: Application
    private lateinit var dummy: MutableList<ModelMovie>

    @Before
    fun setUp() {
        application = ApplicationProvider.getApplicationContext()
        viewModel = ListMovieViewModel()
        dummy = DataDummy.getDummyMovie(application)
    }

    @Test
    fun getListMovie() {
        val movieEntities = viewModel.getListMovie("0")
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities.size)
        assertEquals(movieEntities[0].backdrop_path, dummy[0].backdrop_path)
        assertEquals(movieEntities[0].genre_ids, dummy[0].genre_ids)
        assertEquals(movieEntities[0].id, dummy[0].id)
        assertEquals(movieEntities[0].original_language, dummy[0].original_language)
        assertEquals(movieEntities[0].original_title, dummy[0].original_title)
        assertEquals(movieEntities[0].overview, dummy[0].overview)
        assertEquals(movieEntities[0].popularity, dummy[0].popularity)
        assertEquals(movieEntities[0].poster_path, dummy[0].poster_path)
        assertEquals(movieEntities[0].release_date, dummy[0].release_date)
        assertEquals(movieEntities[0].title, dummy[0].title)
        assertEquals(movieEntities[0].vote_average, dummy[0].vote_average)
        assertEquals(movieEntities[0].vote_count, dummy[0].vote_count)

    }

}