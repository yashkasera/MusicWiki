package com.yashkasera.musicwiki.ui.genredetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yashkasera.musicwiki.databinding.FragmentRecyclerviewBinding
import com.yashkasera.musicwiki.model.Album
import com.yashkasera.musicwiki.model.Artist
import com.yashkasera.musicwiki.model.Genre
import com.yashkasera.musicwiki.model.Track
import com.yashkasera.musicwiki.ui.adapter.AlbumRecyclerAdapter
import com.yashkasera.musicwiki.ui.adapter.ArtistRecyclerAdapter
import com.yashkasera.musicwiki.ui.adapter.TrackRecyclerAdapter
import com.yashkasera.musicwiki.ui.custom.BaseFragment
import com.yashkasera.musicwiki.util.ItemClickListener
import com.yashkasera.musicwiki.util.Result
import com.yashkasera.musicwiki.util.SpacingItemDecoration
import com.yashkasera.musicwiki.util.showAppropriateMessage

class TabFragment(private val position: Int, private val genre: Genre) : BaseFragment(), ItemClickListener {
    private var _binding: FragmentRecyclerviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[GenreDetailsViewModel::class.java]
    }

    private val adapter = when (position) {
        0 -> AlbumRecyclerAdapter()
        1 -> ArtistRecyclerAdapter()
        else -> TrackRecyclerAdapter()
    }.apply {
        itemClickListener = this@TabFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (position) {
            0 -> viewModel.getAlbums(genre)
            1 -> viewModel.getArtists(genre)
            else -> viewModel.getTracks(genre)
        }
        binding.initUI()
        setObservers()
    }

    private fun FragmentRecyclerviewBinding.initUI() {
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpacingItemDecoration())
    }

    private fun setObservers() {
        viewModel.albumResult.observe(viewLifecycleOwner) {
            toggleLoading(it)
            when (it) {
                is Result.Error -> it.exception.showAppropriateMessage()
                is Result.Success -> (adapter as AlbumRecyclerAdapter).submitList(it.data)
                else -> Unit
            }
        }
        viewModel.artistResult.observe(viewLifecycleOwner) {
            toggleLoading(it)
            when (it) {
                is Result.Error -> it.exception.showAppropriateMessage()
                is Result.Success -> (adapter as ArtistRecyclerAdapter).submitList(it.data)
                else -> Unit
            }
        }
        viewModel.trackResult.observe(viewLifecycleOwner) {
            toggleLoading(it)
            when (it) {
                is Result.Error -> it.exception.showAppropriateMessage()
                is Result.Success -> (adapter as TrackRecyclerAdapter).submitList(it.data)
                else -> Unit
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun <T> onItemClick(position: Int, item: T, view: View?) {
        Log.d("TabFragment.kt", "YASH => onItemClick:93 $item")
        when (item) {
            is Album -> {
                val action = GenreDetailsFragmentDirections.actionGenreDetailsFragmentToAlbumFragment(
                    album = item.name,
                    artist = item.getArtistName()
                )
                navigate(action)
            }
            is Artist -> {
                val action = GenreDetailsFragmentDirections.actionGenreDetailsFragmentToArtistFragment(item.name)
                navigate(action)
            }
            is Track -> {
                val action = GenreDetailsFragmentDirections.actionGenreDetailsFragmentToTrackFragment(
                    track = item.name,
                    artist = item.artist.name
                )
                navigate(action)
            }
            else -> Unit
        }
    }

}