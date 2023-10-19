package com.example.technical_test_novian.ui.add_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technical_test_novian.R
import com.example.technical_test_novian.common.base.BaseFragment
import com.example.technical_test_novian.databinding.FragmentAddUserBinding
import dagger.hilt.android.AndroidEntryPoint
import koleton.api.loadSkeleton

@AndroidEntryPoint
class AddUserFragment : BaseFragment<FragmentAddUserBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddUserBinding
        get() = FragmentAddUserBinding::inflate

    private lateinit var spinner: AutoCompleteTextView

    private val viewModel: AddUserViewModel by viewModels()

    override fun setup() {
        setSpinner()
        register()
        observeError()
        getBack()
        validateUserInput()
    }

    private fun register() {

        with(binding) {

            btnSave.setOnClickListener {
                val uid = binding.etAddId.text.toString()
                val uName = binding.etAddName.text.toString()
                val uPw = binding.etAddPassword.text.toString()
                val roles = binding.spinnerRoles.text.toString()

                viewModel.saveNewUser(uid, uName, uPw, roles)
            }

        }

    }

    private fun setSpinner() {
        spinner = binding.spinnerRoles

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.roles,
            android.R.layout.simple_dropdown_item_1line
        )

        spinner.setAdapter(adapter)
    }

    private fun observeError() {
        viewModel.addUserStatus.observe(viewLifecycleOwner) {
            // Handle later
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getBack() {
        binding.topAppBar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager
                .popBackStack()
        }
    }

    /* AI: Handler to check user input. Code later */
    private fun validateUserInput() {

    }

}