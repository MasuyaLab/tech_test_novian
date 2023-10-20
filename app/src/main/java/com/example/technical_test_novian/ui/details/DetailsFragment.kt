package com.example.technical_test_novian.ui.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.technical_test_novian.R
import com.example.technical_test_novian.common.base.BaseFragment
import com.example.technical_test_novian.databinding.FragmentDetailsBinding
import com.example.technical_test_novian.domain.model.User
import com.example.technical_test_novian.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var spinner: AutoCompleteTextView

    override fun setup() {
        setSpinner()

        val receivedUid = arguments?.getString("userId")
        if (receivedUid != null) {
            viewModel.getUser(receivedUid)

            setTopAppBarTitle(receivedUid)
            showDialog(receivedUid)
            editUser(receivedUid)
        }

        observeData()

        validateUserInput()
    }

    private fun observeData() {
        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DataState.Loading -> { /* Not Implemented */
                }

                is DataState.Failure -> { /* AI: Handler select user is failed. Code later */
                }

                is DataState.Success -> {
                    setView(state.data)
                }

                else -> {}
            }
        }

        viewModel.processStatus.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTopAppBarTitle(title: String) {
        binding.topAppBar.title = title
    }

    private fun setView(user: User) {
        with(binding) {
            val role = viewModel.roleMap[user.kdRole]
            Log.i("Details", "role is $role")
            etAddName.setText(user.uName)
            etAddPassword.setText(user.uPw)
            spinner.setText(role, false)
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

    private fun editUser(uid: String) {
        with(binding) {
            btnSave.setOnClickListener {
                val uName = etAddName.text.toString()
                val password = etAddPassword.text.toString()
                val role = spinner.text.toString()

                viewModel.editUser(
                    uid = uid,
                    uName = uName,
                    uPw = password,
                    role = role
                )
            }
        }
    }

    private fun showDialog(uid: String) {
        binding.btnDeleteUser.setOnClickListener {
            val dialog = DeleteUserDialog(
                onYesClickListener = {
                    viewModel.deleteUser(uid = uid)
                }
            )
            dialog.show(parentFragmentManager, "dialog_delete_user")
        }
    }

    /* AI: Handler to validate if input is same with current user. Code later */
    private fun validateUserInput(): Boolean {
        // hardcode
        return false
    }

}