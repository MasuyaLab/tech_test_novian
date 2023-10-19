package com.example.technical_test_novian.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.technical_test_novian.R
import com.example.technical_test_novian.common.base.BaseFragment
import com.example.technical_test_novian.databinding.FragmentHomeBinding
import com.example.technical_test_novian.domain.model.User
import com.example.technical_test_novian.ui.add_user.AddUserFragment
import com.example.technical_test_novian.utils.DataState
import com.facebook.shimmer.Shimmer
import dagger.hilt.android.AndroidEntryPoint
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var fragmentManager: FragmentManager

    override fun setup() {
        fragmentManager = requireActivity().supportFragmentManager
        initView()
        addUserNavigation()
    }

    private fun initView() {
        initRecyclerView()
        viewModel.dataState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun initRecyclerView() {
        recyclerView = binding.rvListUser
        userAdapter = UserAdapter()
        recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun render(state: DataState<List<User>>) {
        when(state) {

            is DataState.Loading -> {
                recyclerView.loadSkeleton(R.layout.user_item_list) {
                    val customShimmer = Shimmer.AlphaHighlightBuilder()
                        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                        .build()

                    shimmer(customShimmer)
                }
            }

            is DataState.Failure -> {
                // Handle Later
                recyclerView.hideSkeleton()
            }

            is DataState.Success -> {
                recyclerView.hideSkeleton()
                userAdapter.submitList(state.data)
            }

        }
    }

    private fun addUserNavigation() {
        val addUserFragment = AddUserFragment()
        binding.addUser.setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, addUserFragment)
                .addToBackStack(null)
                .commit()
        }
    }

}