package com.example.android.roomwordssample.feature.user

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

import com.example.android.roomwordssample.data.database.UserRoomDatabase
import com.example.android.roomwordssample.data.entity.User
import com.example.android.roomwordssample.data.dao.UserDao

class UserRepository(application: Application) {

    private val mUserDao: UserDao
    private val mAllUsers: LiveData<List<User>>

    init {
        val db = UserRoomDatabase.getDatabase(application)
        mUserDao = db.userDao()
        mAllUsers = mUserDao.alphabetizedUsers
    }

    fun getmAllUsers(): LiveData<List<User>> {
        return mAllUsers
    }

    fun insert(user: User) {
        insertAsyncTask(mUserDao).execute(user)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg params: User): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}
