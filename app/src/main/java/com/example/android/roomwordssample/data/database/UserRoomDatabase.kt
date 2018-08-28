package com.example.android.roomwordssample.data.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

import com.example.android.roomwordssample.data.dao.UserDao
import com.example.android.roomwordssample.data.entity.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    private class PopulateDbAsync internal constructor(db: UserRoomDatabase) : AsyncTask<Void, Void, Void>() {

        private val mDao: UserDao

        init {
            mDao = db.userDao()
        }

        override fun doInBackground(vararg params: Void): Void? {
            mDao.deleteAll()
            return null
        }
    }

    companion object {

        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context): UserRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(UserRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<UserRoomDatabase>(context.applicationContext,
                                UserRoomDatabase::class.java!!, "user_database")
                                .fallbackToDestructiveMigration()
                                .addCallback(sRoomDatabaseCallback)
                                .build()
                    }
                }
            }
            return INSTANCE
        }

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
    }

}
