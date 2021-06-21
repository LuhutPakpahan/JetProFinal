package robert.pakpahan.jetprofinal.ui.viewmodel

import androidx.lifecycle.ViewModel
import robert.pakpahan.jetprofinal.source.local.MovieCatalogueRepository
import robert.pakpahan.jetprofinal.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val repository: MovieCatalogueRepository) : ViewModel() {
    fun getFavTvShows() = repository.getFavoriteTvShows()

    fun setFavTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFav
        repository.setFavoriteTvShow(tvShowEntity, newState)
    }
}