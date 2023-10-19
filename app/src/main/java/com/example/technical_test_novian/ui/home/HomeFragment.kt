package com.example.technical_test_novian.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.technical_test_novian.R
import com.example.technical_test_novian.common.base.BaseFragment
import com.example.technical_test_novian.databinding.FragmentHomeBinding
import com.example.technical_test_novian.domain.model.User
import com.example.technical_test_novian.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeViewModel by viewModels()

    override fun setup() {
        initView()
    }

    private fun initView() {
        initRecyclerView()
        viewModel.dataState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun initRecyclerView() {

    }

    private fun render(state: DataState<List<User>>) {
        when(state) {

            is DataState.Loading -> {
                Log.i("HOME", "DATA IS LOAD")
            }

            is DataState.Failure -> {
                Log.i("HOME", "size is ${state.message}")
            }

            is DataState.Success -> {
                Log.i("HOME", "size is ${state.data.size}")
            }

        }
    }


}