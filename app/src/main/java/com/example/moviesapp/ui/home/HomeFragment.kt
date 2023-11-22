package com.example.moviesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.ui.home.adapter.GenresAdapter
import com.example.moviesapp.ui.home.adapter.LastMoviesAdapter
import com.example.moviesapp.util.initRecycler
import com.example.moviesapp.util.showInvisible
import com.example.moviesapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Call api
        viewModel.loadGenresList()
        viewModel.loadLastMoviesList()
        //InitViews
        binding.apply {
            //Get genres
            viewModel.genresList.observe(viewLifecycleOwner) {
                genresAdapter.differ.submitList(it)
                genresRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    genresAdapter
                )
            }
            //Get last movies
            viewModel.lastMoviesList.observe(viewLifecycleOwner) {
                lastMoviesAdapter.setData(it.data)
                //RecyclerView
                lastMoviesRecycler.initRecycler(
                    LinearLayoutManager(requireContext()),
                    lastMoviesAdapter
                )
            }

            //Loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    moviesLoading.showInvisible(true)
                    moviesScrollLay.showInvisible(false)
                } else {
                    moviesLoading.showInvisible(false)
                    moviesScrollLay.showInvisible(true)
                }
            }
        }
    }
}
