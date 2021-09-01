package com.guerrero.upcomingmovies.movies.watchlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.databinding.FragmentWatchlistBinding
import com.guerrero.upcomingmovies.movies.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class WatchlistFragment : Fragment() {

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

    }
}
