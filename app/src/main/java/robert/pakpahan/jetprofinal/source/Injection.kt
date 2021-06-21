package robert.pakpahan.jetprofinal.source

import android.content.Context
import robert.pakpahan.jetprofinal.source.local.MovieCatalogueRepository
import robert.pakpahan.jetprofinal.source.local.database.FilmDatabase
import robert.pakpahan.jetprofinal.source.local.entity.LocalDataSource
import robert.pakpahan.jetprofinal.source.network.RemoteDataSource
import robert.pakpahan.jetprofinal.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {
        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()
        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}