package com.example.technical_test_novian.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_test_novian.domain.model.User
import com.example.technical_test_novian.domain.repository.UserRepository
import com.example.technical_test_novian.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @Named("IO") private val io: CoroutineDispatcher
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<User>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<User>>> = _dataState

    init {
        fetchUserList()
    }

    private fun fetchUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataState.postValue(DataState.Loading)
            userRepository.getListUser()
                .zip(userRepository.getListRoles()) { users, roles ->
                    val map = mutableMapOf<Int, String>()
                    roles.map {
                        map.put(it.kdRole, it.nmRole)
                    }

                    return@zip users.map {
                        it.copy(
                            role = map[it.kdRole] ?: "Unknown Role"
                        )
                    }
                }
                .flowOn(io)
                .catch {
                    _dataState.postValue(DataState.Failure(it.message ?: "Error Occurred!"))
                }
                .collect {
                    _dataState.postValue(DataState.Success(it))
                }
        }
    }
}