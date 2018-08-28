package com.example.android.roomwordssample.feature.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.example.android.roomwordssample.R

class NewUserActivity : AppCompatActivity() {

    private var mEditUserView: EditText? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        mEditUserView = findViewById(R.id.edit_user)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(mEditUserView!!.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val user = mEditUserView!!.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, user)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {

        val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}

