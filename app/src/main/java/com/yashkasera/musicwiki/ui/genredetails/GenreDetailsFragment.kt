package com.yashkasera.musicwiki.ui.genredetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.yashkasera.musicwiki.databinding.FragmentGenreDetailsBinding
import com.yashkasera.musicwiki.ui.custom.BaseFragment
import com.yashkasera.musicwiki.util.Result

class GenreDetailsFragment : BaseFragment() {
    private var _binding: FragmentGenreDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[GenreDetailsViewModel::class.java]
    }
    private val args by navArgs<GenreDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGenreInfo(args.genre)
        binding.initUI()
        setObservers()
    }

    private fun setObservers() {
        viewModel.genreResult.observe(viewLifecycleOwner) {
            if (it is Result.Success) {
                binding.tvDescription.text = it.data.wiki?.summary
            }
        }
    }

    private fun FragmentGenreDetailsBinding.initUI() {
        tvTitle.text = args.genre.name
        viewPager2.adapter = GenrePagerAdapter(childFragmentManager, lifecycle, args.genre)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "Albums"
                1 -> "Artists"
                else -> "Tracks"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}