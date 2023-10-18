package com.example.technical_test_novian.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.technical_test_novian.databinding.UserItemListBinding
import com.example.technical_test_novian.domain.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserListViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.UID == newItem.UID
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    fun submitList(item: List<User>) = differ.submitList(item)


    inner class UserListViewHolder(private val binding: UserItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {

            with(binding) {

                tvUserId.text = item.UID
                tvUserName.text = item.uName

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}