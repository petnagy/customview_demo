package com.petnagy.customviewdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.buttonBack

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    companion object {
        fun createLaunchIntent(context: Context) = Intent(context, SecondActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buttonBack.setOnClickListener {
            finish()
        }
    }
}
