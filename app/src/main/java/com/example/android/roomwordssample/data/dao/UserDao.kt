package com.example.android.roomwordssample.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import com.example.android.roomwordssample.data.entity.User

@Dao
interface UserDao {

    @get:Query("SELECT * from user_table ORDER BY user ASC")
    val alphabetizedUsers: LiveData<List<User>>

    @Insert
    fun insert(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()
}
