package robert.pakpahan.jetprofinal.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
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
import robert.pakpahan.jetprofinal.utils.DataDummy

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {

    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun getFavTvShows() {
        val dummyTvShow = pagedList
        Mockito.`when`(dummyTvShow.size).thenReturn(3)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(movieCatalogueRepository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getFavTvShows().value
        verify(movieCatalogueRepository).getFavoriteTvShows()
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getFavTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun setFavMovie() {
        viewModel.setFavTvShow(DataDummy.getDetailTvShow())
        verify(movieCatalogueRepository).setFavoriteTvShow(DataDummy.getDetailTvShow(), true)
        verifyNoMoreInteractions(movieCatalogueRepository)
    }
}