package com.example.technical_test_novian.ui.details

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.technical_test_novian.databinding.DeleteConfirmationDialogBinding

class DeleteUserDialog(
    private val onYesClickListener: () -> Unit
) : DialogFragment() {

    private var _binding: DeleteConfirmationDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DeleteConfirmationDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val window = dialog?.window

        window?.setLayout(800, 400)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.yesOption.setOnClickListener {
            onYesClickListener.invoke()
            dismiss()
        }

        binding.noOption.setOnClickListener {
            dismiss()
        }
    }
}