package com.junDev.udemyclone.presentation.home_fragment

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.junDev.udemyclone.common.base.BaseFragment
import com.junDev.udemyclone.databinding.FragmentHomeBinding
import com.junDev.udemyclone.domain.home_fragment.model.CoursesResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>()  {

    private val viewModel: HomeViewModel by viewModels()

    override fun bind(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    private fun observeCourses() {
        viewModel.courses.flowWithLifecycle(
            lifecycle, Lifecycle.State.STARTED
        ).onEach {
            handleCourses(it)
        }.launchIn(lifecycleScope)
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach {
            handleState(it)
        }.launchIn(lifecycleScope)
    }

    private fun handleState(state: HomeViewModel.HomeFragmentState) {
        when (state) {
            is HomeViewModel.HomeFragmentState.IsLoading -> handleLoading(state.isLoading)
            is HomeViewModel.HomeFragmentState.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            is HomeViewModel.HomeFragmentState.Init -> Unit
        }
    }

    private fun handleLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }

    private fun handleCourses(courses: CoursesResponse) {
        println("$courses")
    }

    override fun setupObservers() {
        observeState()
        observeCourses()
    }

    override fun setupUI() {

    }
}