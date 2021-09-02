package com.guerrero.upcomingmovies.movies.watchlist

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.databinding.FragmentWatchlistBinding
import com.guerrero.upcomingmovies.movies.MovieClickListener
import com.guerrero.upcomingmovies.movies.MoviesViewModel
import com.guerrero.upcomingmovies.movies.details.MovieDetailsActivity
import com.guerrero.upcomingmovies.shared.Movie
import com.guerrero.upcomingmovies.shared.PARAM_MOVIE
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class WatchlistFragment : Fragment(), MovieClickListener {

    private lateinit var binding: FragmentWatchlistBinding

    private val moviesViewModel: MoviesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.btnAddMovie.setOnClickListener {
            it.findNavController().navigate(
                WatchlistFragmentDirections.actionWatchlistFragmentToUpcomingListFragment()
            )
        }
        setupRecyclerList()
        observeWatchList()
        moviesViewModel.getWatchlist()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                AuthUI.getInstance().signOut(requireContext())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerList() {
        binding.watchlistRecyclerView.adapter = WatchlistAdapter(this)
    }

    override fun onMovieClicked(movie: Movie) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java).apply {
            putExtra(PARAM_MOVIE, movie)
        }
        startActivity(intent)
    }

    private fun observeWatchList() {
        moviesViewModel.getWatchListObservable().observe(viewLifecycleOwner, { watchlist ->
            if (watchlist.isNotEmpty()) {
                with(binding) {
                    (watchlistRecyclerView.adapter as WatchlistAdapter).submitList(watchlist)
                    noMoviesImage.visibility = View.GONE
                    noMoviesText.visibility = View.GONE
                }
            }
        })
    }
}
