package com.leinaro.recipes.ui.common

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ViewEvent {
    data class NavigateTo(val route: String) : ViewEvent()
    object PopBackStack : ViewEvent()
    data class FinishActivity(val paymentId: Long) : ViewEvent()
    data class ShowSnackBar(val text: String) : ViewEvent()
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

abstract class BaseViewModel<T> : ViewModel() {

    private val _uiState = MutableStateFlow<T?>(null)
    val uiState: StateFlow<T?> = _uiState.asStateFlow()

    private val _action = MutableSharedFlow<ViewEvent>()
    val action: SharedFlow<ViewEvent> = _action

    fun setValue(value: T?){
        _uiState.value = value
    }

    fun emitAction(viewEvent: ViewEvent){
        viewModelScope.launch {
            _action.emit(viewEvent)
        }
    }
}