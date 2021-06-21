package robert.pakpahan.jetprofinal.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import robert.pakpahan.jetprofinal.source.local.MovieCatalogueRepository
import robert.pakpahan.jetprofinal.source.local.entity.MovieEntity
import robert.pakpahan.jetprofinal.vo.Resource

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedList)
        Mockito.`when`(dummyMovies.data?.size).thenReturn(3)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        Mockito.`when`(movieCatalogueRepository.getMovies("BEST")).thenReturn(movies)
        val movie = viewModel.getMovies("BEST").value?.data
        verify(movieCatalogueRepository).getMovies("BEST")
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getMovies("BEST").observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}