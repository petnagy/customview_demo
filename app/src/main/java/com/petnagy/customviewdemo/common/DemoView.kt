package com.petnagy.customviewdemo.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.petnagy.customviewdemo.R
import kotlinx.android.synthetic.main.view_demo.view.demoCheckBox
import kotlinx.android.synthetic.main.view_demo.view.demoLabel
import kotlinx.android.synthetic.main.view_demo.view.demoRadio

class DemoView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_demo, this, true)

        attrs?.let { attributeSet ->
            val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.DemoView, 0, 0)
            val textResId = typedArray.getResourceId(R.styleable.DemoView_label, 0)
            val labelText = typedArray.getText(R.styleable.DemoView_label)
            val radioButtonTextResId = typedArray.getResourceId(R.styleable.DemoView_radioButton, 0)
            val radioButtonText = typedArray.getText(R.styleable.DemoView_radioButton)
            val checkBoxResId = typedArray.getResourceId(R.styleable.DemoView_checkBox, 0)
            val checkBoxText = typedArray.getText(R.styleable.DemoView_checkBox)
            typedArray.recycle()

            demoLabel.text = if (textResId > 0) {
                resources.getText(textResId)
            } else {
                labelText ?: ""
            }

            demoRadio.text = if (radioButtonTextResId > 0) {
                resources.getText(radioButtonTextResId)
            } else {
                radioButtonText ?: ""
            }

            demoCheckBox.text = if (checkBoxResId > 0) {
                resources.getText(checkBoxResId)
            } else {
                checkBoxText ?: ""
            }

            demoRadio.isClickable = false
            setOnClickListener {
                demoRadio.callOnClick()
            }
        }
    }

    fun setRadioButton(checked: Boolean) {
        demoRadio.isChecked = checked
    }

}
