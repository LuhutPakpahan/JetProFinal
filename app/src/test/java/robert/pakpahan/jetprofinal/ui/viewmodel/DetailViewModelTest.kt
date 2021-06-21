package robert.pakpahan.jetprofinal.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
import robert.pakpahan.jetprofinal.source.local.entity.MovieEntity
import robert.pakpahan.jetprofinal.source.local.entity.TvShowEntity
import robert.pakpahan.jetprofinal.ui.viewmodel.DetailViewModel.Companion.MOVIE
import robert.pakpahan.jetprofinal.ui.viewmodel.DetailViewModel.Companion.TV_SHOW
import robert.pakpahan.jetprofinal.utils.DataDummy
import robert.pakpahan.jetprofinal.vo.Resource

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.getDetailMovie()
    private val dummyMovieId = dummyMovie.id

    private val dummyTvShow = DataDummy.getDetailTvShow()
    private val dummyTvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    // Get Data Movie Testing
    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel(movieCatalogueRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetailMovie = Resource.success(DataDummy.getDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        Mockito.`when`(movieCatalogueRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)

        viewModel.setFilm(dummyMovieId.toString(), MOVIE)
        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.getDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        Mockito.`when`(movieCatalogueRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)

        viewModel.setFilm(dummyMovieId.toString(), MOVIE)
        viewModel.setFavoriteMovie()
        verify(movieCatalogueRepository).setFavoriteMovie(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    // Get Data TV Show Testing
    @Before
    fun setupTvShow() {
        viewModel = DetailViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShowDetail() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        Mockito.`when`(movieCatalogueRepository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setFilm(dummyTvShowId.toString(), TV_SHOW)
        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        Mockito.`when`(movieCatalogueRepository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setFilm(dummyTvShowId.toString(), TV_SHOW)
        viewModel.setFavoriteTvShow()
        verify(movieCatalogueRepository).setFavoriteTvShow(tvShow.value!!.data as TvShowEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}