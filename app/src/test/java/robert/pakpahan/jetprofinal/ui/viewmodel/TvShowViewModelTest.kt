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
import robert.pakpahan.jetprofinal.source.local.entity.TvShowEntity
import robert.pakpahan.jetprofinal.vo.Resource

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = Resource.success(pagedList)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(3)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(movieCatalogueRepository.getTvShows("BEST")).thenReturn(tvShows)
        val tvShow = viewModel.getTvShows("BEST").value?.data
        verify(movieCatalogueRepository).getTvShows("BEST")
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getTvShows("BEST").observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}