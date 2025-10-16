package com.octane.pbd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octane.pbd.data.BreachResult
import com.octane.pbd.data.PwnedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val loading: Boolean = false,
    val result: BreachResult? = null,
    val error: String? = null
)

@HiltViewModel
class CheckViewModel @Inject constructor(
    private val repo: PwnedRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun check(password: String) {
        if (password.isEmpty()) {
            _state.value = UiState(error = "Password can't be empty")
            return
        }
        _state.value = UiState(loading = true)
        viewModelScope.launch {
            try {
                val res = repo.checkPassword(password)
                _state.value = UiState(result = res)
            } catch (e: Exception) {
                _state.value = UiState(error = e.message ?: "Something went wrong")
            }
        }
    }

    fun clearError() { _state.value = _state.value.copy(error = null) }
}
