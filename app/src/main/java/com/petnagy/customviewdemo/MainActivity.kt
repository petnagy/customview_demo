package com.petnagy.customviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petnagy.customviewdemo.common.DemoView
import kotlinx.android.synthetic.main.activity_main.demoButton
import kotlinx.android.synthetic.main.activity_main.demoDemo1
import kotlinx.android.synthetic.main.activity_main.demoDemo2
import kotlinx.android.synthetic.main.activity_main.demoDemo3
import kotlinx.android.synthetic.main.activity_main.demoDemo4

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        private const val VIEW1_STATE = "view1State"
        private const val VIEW2_STATE = "view2State"
        private const val VIEW3_STATE = "view3State"
        private const val VIEW4_STATE = "view4State"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        demoButton.setOnClickListener {
            val intent = SecondActivity.createLaunchIntent(this)
            startActivity(intent)
        }
//        if (savedInstanceState != null) {
//            val view1State = savedInstanceState.getParcelable(VIEW1_STATE) as DemoView.DemoViewState?
//            val view2State = savedInstanceState.getParcelable(VIEW2_STATE) as DemoView.DemoViewState?
//            val view3State = savedInstanceState.getParcelable(VIEW3_STATE) as DemoView.DemoViewState?
//            val view4State = savedInstanceState.getParcelable(VIEW4_STATE) as DemoView.DemoViewState?
//            view1State?.let {
//                demoDemo1.setUiState(it)
//            }
//            view2State?.let {
//                demoDemo2.setUiState(it)
//            }
//            view3State?.let {
//                demoDemo3.setUiState(it)
//            }
//            view4State?.let {
//                demoDemo4.setUiState(it)
//            }
//        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putParcelable(VIEW1_STATE, demoDemo1.getUiState())
//        outState.putParcelable(VIEW2_STATE, demoDemo2.getUiState())
//        outState.putParcelable(VIEW3_STATE, demoDemo3.getUiState())
//        outState.putParcelable(VIEW4_STATE, demoDemo4.getUiState())
//    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        val view1State = savedInstanceState.getParcelable(VIEW1_STATE) as DemoView.DemoViewState?
//        val view2State = savedInstanceState.getParcelable(VIEW2_STATE) as DemoView.DemoViewState?
//        val view3State = savedInstanceState.getParcelable(VIEW3_STATE) as DemoView.DemoViewState?
//        val view4State = savedInstanceState.getParcelable(VIEW4_STATE) as DemoView.DemoViewState?
//        view1State?.let {
//            demoDemo1.setUiState(it)
//        }
//        view2State?.let {
//            demoDemo2.setUiState(it)
//        }
//        view3State?.let {
//            demoDemo3.setUiState(it)
//        }
//        view4State?.let {
//            demoDemo4.setUiState(it)
//        }
//    }
}
