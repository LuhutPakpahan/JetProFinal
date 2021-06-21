package robert.pakpahan.jetprofinal.source.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import robert.pakpahan.jetprofinal.source.remote.response.movie.MovieDetailResponse
import robert.pakpahan.jetprofinal.source.remote.response.movie.MoviesResponse
import robert.pakpahan.jetprofinal.source.remote.response.tvshow.TvShowDetailResponse
import robert.pakpahan.jetprofinal.source.remote.response.tvshow.TvShowResponse

interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): Call<MovieDetailResponse>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    @GET("tv/{id}")
    fun getTvShowDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): Call<TvShowDetailResponse>
}