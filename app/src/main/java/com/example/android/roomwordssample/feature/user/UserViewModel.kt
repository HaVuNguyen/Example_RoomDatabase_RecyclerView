package com.example.android.roomwordssample.feature.user

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.example.android.roomwordssample.data.entity.User

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        mRepository = UserRepository(application)
        allUsers = mRepository.getmAllUsers()
    }

    fun insert(user: User) {
        mRepository.insert(user)
    }
}