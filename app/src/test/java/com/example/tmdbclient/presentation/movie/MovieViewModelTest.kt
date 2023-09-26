package com.example.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.FakeMovieRepository
import com.example.tmdbclient.domain.usecase.GetMovieUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMoviesUseCase = GetMovieUseCase(fakeMovieRepository)
        val updateMovieUseCase = UpdateMoviesUseCase(fakeMovieRepository)
        viewModel = MovieViewModel(getMoviesUseCase, updateMovieUseCase)
    }

    @Test
    fun getMovies_returnsCurrentList() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(1, "overview1", "posterPath1", "releaseDate1", "title1"))
        movies.add(Movie(2, "overview2", "posterPath2", "releaseDate2", "title2"))

        val currentList = viewModel.getMovies().getOrAwaitValue()
        Truth.assertThat(currentList).isEqualTo(movies)
    }

    @Test
    fun updateMovies_returnsUpdatedList() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(3, "overview3", "posterPath3", "releaseDate3", "title3"))
        movies.add(Movie(4, "overview4", "posterPath4", "releaseDate4", "title4"))

        val updatedList = viewModel.updateMovies().getOrAwaitValue()
        Truth.assertThat(updatedList).isEqualTo(movies)
    }


}