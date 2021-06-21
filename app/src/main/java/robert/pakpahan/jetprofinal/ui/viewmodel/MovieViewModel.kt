package robert.pakpahan.jetprofinal.ui.viewmodel

import androidx.lifecycle.ViewModel
import robert.pakpahan.jetprofinal.source.local.MovieCatalogueRepository

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovies(sort: String) = movieCatalogueRepository.getMovies(sort)
}