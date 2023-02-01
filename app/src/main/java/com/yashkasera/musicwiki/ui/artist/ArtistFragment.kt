package com.yashkasera.musicwiki.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.yashkasera.musicwiki.databinding.FragmentArtistBinding
import com.yashkasera.musicwiki.model.Album
import com.yashkasera.musicwiki.model.Artist
import com.yashkasera.musicwiki.ui.adapter.AlbumRecyclerAdapter
import com.yashkasera.musicwiki.ui.adapter.ArtistRecyclerAdapter
import com.yashkasera.musicwiki.ui.custom.BaseFragment
import com.yashkasera.musicwiki.util.ItemClickListener
import com.yashkasera.musicwiki.util.Result
import com.yashkasera.musicwiki.util.SpacingItemDecoration
import com.yashkasera.musicwiki.util.showAppropriateMessage

class ArtistFragment : BaseFragment(), ItemClickListener {
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[ArtistViewModel::class.java]
    }

    private val args by navArgs<ArtistFragmentArgs>()

    private val similarArtistAdapter = ArtistRecyclerAdapter().apply {
        itemClickListener = this@ArtistFragment
        isHorizontal = true
    }
    private val topAlbumAdapter = AlbumRecyclerAdapter().apply {
        itemClickListener = this@ArtistFragment
        isHorizontal = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initUI()
        setObservers()
        viewModel.fetchData(args.name)
    }

    private fun setObservers() {
        viewModel.topAlbumResult.observe(viewLifecycleOwner) {
            toggleLoading(it)
            when (it) {
                is Result.Success -> topAlbumAdapter.submitList(it.data)
                is Result.Error -> it.exception.showAppropriateMessage()
                is Result.Loading -> Unit
            }
        }
        viewModel.artistResult.observe(viewLifecycleOwner) {
            toggleLoading(it)
            when (it) {
                is Result.Success -> {
                    binding.artist = it.data
                    if (it.data.similar?.artist?.isEmpty() == true) binding.tvSimilar.visibility = View.GONE
                    else it.data.similar?.artist?.let { it1 -> similarArtistAdapter.submitList(it1) }
                }
                is Result.Error -> it.exception.showAppropriateMessage()
                is Result.Loading -> Unit
            }
        }
    }

    private fun FragmentArtistBinding.initUI() {
        rvSimilarArtists.adapter = similarArtistAdapter
        rvTopAlbums.adapter = topAlbumAdapter
        val spacingItemDecoration = SpacingItemDecoration(12, 1)
        rvTopAlbums.addItemDecoration(spacingItemDecoration)
        rvSimilarArtists.addItemDecoration(spacingItemDecoration)
    }

    override fun <T> onItemClick(position: Int, item: T, view: View?) {
        when (item) {
            is Artist -> {
                navigate(ArtistFragmentDirections.actionArtistFragmentSelf(item.name))
            }
            is Album -> {
                navigate(
                    ArtistFragmentDirections.actionArtistFragmentToAlbumFragment(
                        album = item.name,
                        artist = item.getArtistName()
                    )
                )
            }
        }
    }
}