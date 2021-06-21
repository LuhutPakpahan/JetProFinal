package robert.pakpahan.jetprofinal.ui.viewmodel

import androidx.lifecycle.ViewModel
import robert.pakpahan.jetprofinal.source.local.MovieCatalogueRepository

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getTvShows(sort: String) = movieCatalogueRepository.getTvShows(sort)
}