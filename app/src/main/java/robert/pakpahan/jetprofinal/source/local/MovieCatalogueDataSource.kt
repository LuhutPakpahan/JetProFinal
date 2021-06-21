package robert.pakpahan.jetprofinal.source.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import robert.pakpahan.jetprofinal.source.local.entity.MovieEntity
import robert.pakpahan.jetprofinal.source.local.entity.TvShowEntity
import robert.pakpahan.jetprofinal.vo.Resource

interface MovieCatalogueDataSource {
    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}