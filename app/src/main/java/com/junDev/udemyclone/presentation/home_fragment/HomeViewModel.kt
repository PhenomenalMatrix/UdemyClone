package com.junDev.udemyclone.presentation.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junDev.udemyclone.common.base.BaseResult
import com.junDev.udemyclone.domain.home_fragment.model.CoursesResponse
import com.junDev.udemyclone.domain.home_fragment.usecase.GetCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
    ): ViewModel() {

    private val _state = MutableStateFlow<HomeFragmentState>(HomeFragmentState.Init)
    val state: StateFlow<HomeFragmentState> get() = _state

    private val _courses = MutableSharedFlow<CoursesResponse>()
    val courses: SharedFlow<CoursesResponse> get() = _courses

    init {
        fetchCourses()
    }

    private fun fetchCourses() {

            viewModelScope.launch {
                getCoursesUseCase.invoke()
                    .onStart {
                        setLoading()
                    }
                    .catch {
                        hideLoading()
                        showToast(it.message)
                    }
                    .collect {
                        hideLoading()
                        when(it){
                            is BaseResult.Success ->{
                                _courses.emit(it.data)
                            }
                            is BaseResult.Error -> {
                                showToast(it.erorrMsg)
                            }
                        }
                    }
            }

    }
    private fun showToast(message: String?) {
        _state.value = HomeFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = HomeFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = HomeFragmentState.IsLoading(true)
    }


    sealed class HomeFragmentState {
        object Init : HomeFragmentState()
        data class IsLoading(val isLoading: Boolean) : HomeFragmentState()
        data class ShowToast(val message: String?) : HomeFragmentState()
    }
}