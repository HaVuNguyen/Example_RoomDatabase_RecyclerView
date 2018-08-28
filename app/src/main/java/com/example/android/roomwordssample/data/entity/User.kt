package com.example.android.roomwordssample.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user_table")
class User(@field:PrimaryKey
           @field:ColumnInfo(name = "user")
           val user: String)