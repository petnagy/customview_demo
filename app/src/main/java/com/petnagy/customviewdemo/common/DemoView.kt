package com.petnagy.customviewdemo.common

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.petnagy.customviewdemo.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.view_demo.view.demoCheckBox
import kotlinx.android.synthetic.main.view_demo.view.demoInputField
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

    fun isRadioButtonOn() = demoRadio.isChecked

    fun isCheckBoxOn() = demoCheckBox.isChecked

    fun getText() = demoInputField.text.toString()

    fun setRadioButtonState(checked: Boolean) {
        demoRadio.isChecked = checked
    }

    fun setCheckBoxState(checked: Boolean) {
        demoCheckBox.isChecked = checked
    }

    fun setText(text: String) {
        demoInputField.setText(text)
    }

    fun getUiState(): DemoViewState {
        return DemoViewState(demoRadio.isChecked, demoCheckBox.isChecked, demoInputField.text.toString())
    }

    fun setUiState(uiState: DemoViewState) {
        demoRadio.isChecked = uiState.radioButtonChecked
        demoCheckBox.isChecked = uiState.checkBoxChecked
        demoInputField.setText(uiState.text)
    }

    @Parcelize
    data class DemoViewState(val radioButtonChecked: Boolean, val checkBoxChecked: Boolean, val text: String): Parcelable
}
