package com.petnagy.customviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.demoButton

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        demoButton.setOnClickListener {
            val intent = SecondActivity.createLaunchIntent(this)
            startActivity(intent)
        }
    }
}
