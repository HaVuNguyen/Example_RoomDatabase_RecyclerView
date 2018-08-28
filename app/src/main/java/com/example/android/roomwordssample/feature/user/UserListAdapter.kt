package com.example.android.roomwordssample.feature.user

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.android.roomwordssample.R
import com.example.android.roomwordssample.data.entity.User


internal class UserListAdapter(context: Context) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    val mInflater: LayoutInflater
    var mUsers: List<User>? = null

    internal inner class UserViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userItemView: TextView

        init {
            userItemView = itemView.findViewById(R.id.textView)
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = mInflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = mUsers!![position]
        holder.userItemView.text = current.user
    }

    fun setUsers(users: List<User>) {
        mUsers = users
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (mUsers != null)
            mUsers!!.size
        else
            0
    }
}


