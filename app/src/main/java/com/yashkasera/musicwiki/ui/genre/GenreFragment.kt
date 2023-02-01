package com.yashkasera.musicwiki.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.yashkasera.musicwiki.databinding.FragmentGenreBinding
import com.yashkasera.musicwiki.model.Genre
import com.yashkasera.musicwiki.ui.custom.BaseFragment
import com.yashkasera.musicwiki.ui.custom.GenreChip
import com.yashkasera.musicwiki.util.Result
import com.yashkasera.musicwiki.util.showAppropriateMessage

class GenreFragment : BaseFragment() {
    private var list: List<Genre> = emptyList()
    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GenreViewModel by lazy {
        ViewModelProvider(this)[GenreViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
        initUI()
    }

    private fun initUI() {
        binding.next.setOnClickListener {
            getSelectedGenreOrNull()?.let {
                navigate(GenreFragmentDirections.actionGenreFragmentToGenreDetailsFragment(it))
            } ?: run {
                Snackbar
                    .make(binding.root, "Select a genre to continue", Snackbar.LENGTH_SHORT)
                    .setAction("Random") {
                        navigate(
                            GenreFragmentDirections.actionGenreFragmentToGenreDetailsFragment(list.random())
                        )
                    }
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.next.isEnabled = getSelectedGenreOrNull() != null
    }

    private fun addObserver() {
        viewModel.result.observe(viewLifecycleOwner) {
            toggleLoading(it)
            when (it) {
                is Result.Error -> it.exception.showAppropriateMessage()
                is Result.Success -> it.data.let { list ->
                    binding.tvMore.isVisible = true
                    list.subList(0, 10).forEach { tag ->
                        binding.flowLayout.addView(getTagView(tag))
                    }
                    this.list = list
                    binding.tvMore.setOnClickListener {
                        binding.tvMore.isVisible = false
                        binding.logo2.isVisible = false
                        list.subList(10, list.size).forEach { tag ->
                            binding.flowLayout.addView(getTagView(tag))
                        }
                    }
                }
                else -> Unit
            }
        }
    }

    private fun getTagView(genre: Genre): View =
        GenreChip(requireContext(), genre).apply {
            setOnClickListener {
                if (genre.isSelected) {
                    genre.isSelected = false
                    onGenreClicked(genre)
                    binding.next.isEnabled = false
                } else {
                    resetSelection()
                    genre.isSelected = true
                    onGenreClicked(genre)
                    toggleNextButton()
                }
            }
        }

    private fun resetSelection() {
        list.forEach { it.isSelected = false }
        binding.flowLayout.children.forEach { child ->
            child.isSelected = false
            child.refreshDrawableState()
        }
    }

    private fun getSelectedGenreOrNull(): Genre? = list.firstOrNull { it.isSelected }

    private fun toggleNextButton() {
        binding.next.isEnabled = getSelectedGenreOrNull() != null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}