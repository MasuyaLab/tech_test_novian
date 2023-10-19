package com.example.technical_test_novian.ui.add_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_test_novian.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @Named("IO") private val io: CoroutineDispatcher
): ViewModel() {

    private val _addUserStatus: MutableLiveData<String> = MutableLiveData()
    val addUserStatus: LiveData<String> get() = _addUserStatus

    private val roleMap = mutableMapOf<Int, String>()

    init {
        getListRoles()
    }

    fun saveNewUser(
        uid: String,
        uName: String,
        password: String,
        role: String
    ) {
        val roleCode = getKeyByValue(roleMap, role)
        viewModelScope.launch(io) {
            userRepository.registerNewUser(
                uid = uid,
                uName = uName,
                uPw = password,
                roleCode.toString()
            )
                .catch { _addUserStatus.postValue(it.message) }
                .collect{ _addUserStatus.postValue(it) }
        }
    }

    private fun getListRoles() {
        viewModelScope.launch {
            userRepository.getListRoles()
                .flowOn(io)
                .collect {
                    it.map { role ->
                        roleMap[role.kdRole] = role.nmRole
                    }
                }
        }
    }

    private fun getKeyByValue(map: Map<Int, String>, value: String): Int {
        for ((key, mapValue) in map) {
            if (mapValue == value) {
                return key
            }
        }
        return 7
    }

}