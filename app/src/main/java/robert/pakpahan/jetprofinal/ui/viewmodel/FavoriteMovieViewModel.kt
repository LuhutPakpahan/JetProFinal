package robert.pakpahan.jetprofinal.ui.viewmodel

import androidx.lifecycle.ViewModel
import robert.pakpahan.jetprofinal.source.local.MovieCatalogueRepository
import robert.pakpahan.jetprofinal.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val repository: MovieCatalogueRepository) : ViewModel() {
    fun getFavMovies() = repository.getFavoriteMovies()

    fun setFavMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFav
        repository.setFavoriteMovie(movieEntity, newState)
    }
}