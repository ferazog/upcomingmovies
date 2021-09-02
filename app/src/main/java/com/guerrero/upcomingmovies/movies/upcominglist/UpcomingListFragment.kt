package com.guerrero.upcomingmovies.movies.upcominglist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.databinding.FragmentUpcomingListBinding
import com.guerrero.upcomingmovies.movies.MovieClickListener
import com.guerrero.upcomingmovies.movies.MoviesViewModel
import com.guerrero.upcomingmovies.movies.details.MovieDetailsActivity
import com.guerrero.upcomingmovies.shared.Movie
import com.guerrero.upcomingmovies.shared.PARAM_MOVIE
import com.guerrero.upcomingmovies.shared.SCROLLING_DOWN
import com.guerrero.upcomingmovies.shared.UPCOMING_GRID_SPAN_COUNT
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UpcomingListFragment : Fragment(), MovieClickListener {

    private lateinit var binding: FragmentUpcomingListBinding

    private val moviesViewModel: MoviesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUpcomingListRecyclerView()
        moviesViewModel.run {
            observeUpcomingList()
            observeUpcomingListEvent()
        }
    }

    private fun observeUpcomingListEvent() {
        moviesViewModel.getUpcomingListEventObservable().observe(viewLifecycleOwner, { event ->
            when (event) {
                UpcomingListEvent.Normal -> {
                    binding.upcomingListRecyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                UpcomingListEvent.Loading -> {
                    binding.upcomingListRecyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UpcomingListEvent.Failed -> {
                    binding.upcomingListRecyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    Toast
                        .makeText(requireContext(), "error: ${event.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun observeUpcomingList() {
        moviesViewModel.getUpcomingListObservable().observe(viewLifecycleOwner, { upcomingList ->
            with(binding.upcomingListRecyclerView) {
                (adapter as? UpcomingListAdapter)?.submitList(upcomingList)
            }
        })
    }

    override fun onMovieClicked(movie: Movie) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java).apply {
            putExtra(PARAM_MOVIE, movie)
        }
        startActivity(intent)
    }

    private fun setupUpcomingListRecyclerView() {
        with(binding.upcomingListRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), UPCOMING_GRID_SPAN_COUNT)
            adapter = UpcomingListAdapter(this@UpcomingListFragment)
            addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollVertically(SCROLLING_DOWN)) {
                            Toast.makeText(
                                requireContext(),
                                R.string.getting_more_movies,
                                Toast.LENGTH_SHORT
                            ).show()
                            moviesViewModel.loadMoreUpcomingMovies()
                        }
                    }
                }
            )
        }
    }
}
