package robert.pakpahan.jetprofinal.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import robert.pakpahan.jetprofinal.source.local.entity.LocalDataSource
import robert.pakpahan.jetprofinal.source.local.entity.MovieEntity
import robert.pakpahan.jetprofinal.source.local.entity.TvShowEntity
import robert.pakpahan.jetprofinal.source.network.RemoteDataSource
import robert.pakpahan.jetprofinal.utils.AppExecutors
import robert.pakpahan.jetprofinal.utils.DataDummy
import robert.pakpahan.jetprofinal.utils.LiveDataTestUtil
import robert.pakpahan.jetprofinal.utils.PagedListUtil
import robert.pakpahan.jetprofinal.vo.Resource

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.getRemoteMovies()
    private val movieId = moviesResponse[0].id
    private val movieDetail = DataDummy.getRemoteDetailMovie()

    private val tvShowResponse = DataDummy.getRemoteTvShows()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetail = DataDummy.getRemoteDetailTvShow()

    @Test
    fun getMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getAllMovies("BEST")).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getMovies("BEST")

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovies()))
        verify(local).getAllMovies("BEST")
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.getDetailMovie()
        Mockito.`when`(local.getMovieById(movieId)).thenReturn(dummyDetail)

        val movieDetailEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailMovie(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavMovies()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovies()))
        verify(local).getFavMovies()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        movieCatalogueRepository.setFavoriteMovie(DataDummy.getDetailMovie(), true)
        verify(local).setFavoriteMovie(DataDummy.getDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getAllTvShows("BEST")).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getTvShows("BEST")

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getTvShows()))
        verify(local).getAllTvShows("BEST")
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.getDetailTvShow()
        Mockito.`when`(local.getTvShowById(tvShowId)).thenReturn(dummyDetail)

        val tvShowDetailEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailTvShow(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getFavTvShows()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoriteTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getTvShows()))
        verify(local).getFavTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        movieCatalogueRepository.setFavoriteTvShow(DataDummy.getDetailTvShow(), true)
        verify(local).setFavoriteTvShow(DataDummy.getDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}