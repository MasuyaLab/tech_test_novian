package com.example.technical_test_novian.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_test_novian.domain.model.User
import com.example.technical_test_novian.domain.repository.UserRepository
import com.example.technical_test_novian.utils.DataState
import com.example.technical_test_novian.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @Named("IO") private val io: CoroutineDispatcher
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<User>> = MutableLiveData()
    val dataState: LiveData<DataState<User>> = _dataState

    private val _processStatus: MutableLiveData<String> = MutableLiveData()
    val processStatus: LiveData<String> = _processStatus

    private val dataUser: MutableLiveData<User> = MutableLiveData()

    var roleMap: Map<String, String> = mapOf()
        private set

    init {
        getListRoles()
    }

    fun getUser(uid: String) {
        viewModelScope.launch(io) {
            userRepository.selectUser(uid)
                .catch {
                    _dataState.postValue(DataState.Failure(it.message ?: "Error Occurred!"))
                }
                .collect {
                    _dataState.postValue(DataState.Success(it))
                    dataUser.postValue(it)
                }
        }
    }

    fun deleteUser(uid: String) {
        viewModelScope.launch(io) {
            userRepository.deleteUser(uid)
                .catch { _processStatus.postValue(it.message) }
                .collect { _processStatus.postValue(it) }
        }
    }

    fun editUser(
        uid: String,
        uName: String,
        uPw: String,
        role: String
    ) {
        viewModelScope.launch(io) {
            val roleCode = Utils.getKeyByValue(roleMap, role)
            userRepository.editUser(uid, uName, uPw, roleCode)
                .catch {
                    _processStatus.postValue(it.message)
                }
                .collect {
                    _processStatus.postValue(it)
                }
        }
    }

    private fun getListRoles() {
        val newMap = mutableMapOf<String, String>()
        viewModelScope.launch(io) {
            userRepository.getListRoles()
                .collect { roles ->
                    roles.forEach { role ->
                        newMap[role.kdRole] = role.nmRole
                    }
                }
        }
        roleMap = newMap
    }

}