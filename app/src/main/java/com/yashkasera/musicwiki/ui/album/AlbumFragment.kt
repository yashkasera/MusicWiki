package com.yashkasera.musicwiki.ui.album

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.yashkasera.musicwiki.R
import com.yashkasera.musicwiki.databinding.FragmentAlbumBinding
import com.yashkasera.musicwiki.model.Track
import com.yashkasera.musicwiki.ui.adapter.TrackRecyclerAdapter
import com.yashkasera.musicwiki.ui.custom.BaseFragment
import com.yashkasera.musicwiki.util.ItemClickListener
import com.yashkasera.musicwiki.util.Result
import com.yashkasera.musicwiki.util.SpacingItemDecoration
import com.yashkasera.musicwiki.util.showAppropriateMessage

class AlbumFragment : BaseFragment(), ItemClickListener {
    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[AlbumViewModel::class.java]
    }

    private val args by navArgs<AlbumFragmentArgs>()

    private val tracksAdapter = TrackRecyclerAdapter().apply {
        itemClickListener = this@AlbumFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initUI()
        setObservers()
        viewModel.fetchData(
            artist = args.artist,
            album = args.album
        )
    }

    private fun setObservers() {
        viewModel.result.observe(viewLifecycleOwner) {
            toggleLoading(it)
            when (it) {
                is Result.Success -> {
                    binding.album = it.data
                    tracksAdapter.submitList(it.data.tracks?.track)
                }
                is Result.Error -> it.exception.showAppropriateMessage()
                is Result.Loading -> Unit
            }
        }
    }

    private fun FragmentAlbumBinding.initUI() {
        handler = this@AlbumFragment
        rvTracks.adapter = tracksAdapter
        val spacingItemDecoration = SpacingItemDecoration(12, 1)
        rvTracks.addItemDecoration(spacingItemDecoration)
    }

    fun onArtistNameClicked(v: View) {
        binding.album?.getArtistName()?.let {
            navigate(AlbumFragmentDirections.actionAlbumFragmentToArtistFragment(name = it))
        }
    }

    override fun <T> onItemClick(position: Int, item: T, view: View?) {
        navigate(
            when (item) {
                is Track -> AlbumFragmentDirections.actionAlbumFragmentToTrackFragment(
                    artist = item.artist.name,
                    track = item.name
                )
                else -> throw IllegalArgumentException("Unknown item type")
            }
        )
    }
}