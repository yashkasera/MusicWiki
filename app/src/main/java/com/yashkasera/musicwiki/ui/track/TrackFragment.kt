package com.yashkasera.musicwiki.ui.track

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.yashkasera.musicwiki.databinding.FragmentTrackBinding
import com.yashkasera.musicwiki.ui.custom.BaseFragment
import com.yashkasera.musicwiki.util.Result
import com.yashkasera.musicwiki.util.showAppropriateMessage

class TrackFragment : BaseFragment() {
    private var _binding: FragmentTrackBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[TrackViewModel::class.java]
    }

    private val args by navArgs<TrackFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.handler = this
        setObservers()
        viewModel.fetchData(
            artist = args.artist,
            track = args.track
        )
    }

    private fun setObservers() {
        viewModel.result.observe(viewLifecycleOwner) {
            toggleLoading(it)
            when (it) {
                is Result.Success -> {
                    Log.d("TrackFragment.kt", "YASH => setObservers:48 ${it.data.album}")
                    binding.track = it.data
                }
                is Result.Error -> it.exception.showAppropriateMessage()
                is Result.Loading -> Unit
            }
        }
    }

    fun viewAlbum(v: View) {
        binding.track?.let {
            navigate(
                TrackFragmentDirections.actionTrackFragmentToAlbumFragment(
                    artist = it.artist.name,
                    album = it.album?.title ?: ""
                )
            )
        }
    }
}