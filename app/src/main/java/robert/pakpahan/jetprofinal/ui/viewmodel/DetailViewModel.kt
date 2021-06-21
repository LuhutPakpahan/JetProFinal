package robert.pakpahan.jetprofinal.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import robert.pakpahan.jetprofinal.source.local.MovieCatalogueRepository
import robert.pakpahan.jetprofinal.source.local.entity.MovieEntity
import robert.pakpahan.jetprofinal.source.local.entity.TvShowEntity
import robert.pakpahan.jetprofinal.vo.Resource

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }

    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun setFilm(id: String, category: String) {
        when (category) {
            TV_SHOW -> {
                detailTvShow = movieCatalogueRepository.getDetailTvShow(id.toInt())
            }

            MOVIE -> {
                detailMovie = movieCatalogueRepository.getDetailMovie(id.toInt())
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            movieCatalogueRepository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            movieCatalogueRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    fun getDetailTvShow() = detailTvShow
    fun getDetailMovie() = detailMovie

}