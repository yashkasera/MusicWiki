package com.yashkasera.musicwiki.ui.custom

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.yashkasera.musicwiki.MainActivity
import com.yashkasera.musicwiki.util.Result

abstract class BaseFragment : Fragment() {
    private fun showLoading() {
        if (isAdded && isVisible) {
            (requireActivity() as MainActivity).toggleLoading(true)
        }
    }

    private fun hideLoading() {
        if (isAdded && isVisible) {
            (requireActivity() as MainActivity).toggleLoading(false)
        }
    }

    fun <T> toggleLoading(result: Result<T>) {
        when (result) {
            is Result.Loading -> showLoading()
            else -> hideLoading()
        }
    }

    fun navigate(action: NavDirections) {
        try {
            findNavController().navigate(action, navOptions {
                anim {
                    enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                    exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}