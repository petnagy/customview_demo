package com.petnagy.customviewdemo.common

import android.content.Context
import android.os.Parcel
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

        // Solve the problem of the don't keep activity's problem however constraints will be wrong
//        demoRadio.id = View.generateViewId()
//        demoCheckBox.id = View.generateViewId()
//        demoInputField.id = View.generateViewId()
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
    data class DemoViewState(val radioButtonChecked: Boolean, val checkBoxChecked: Boolean, val text: String) : Parcelable

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        return if (superState != null) {
            val savedState = SavedState(superState)
            savedState.radioButtonState = demoRadio.isChecked
            savedState.checkBoxState = demoCheckBox.isChecked
            savedState.text = demoInputField.text.toString()
            savedState
        } else {
            null
        }
    }

    public override fun onRestoreInstanceState(state: Parcelable) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            demoRadio.isChecked = state.radioButtonState
            demoCheckBox.isChecked = state.checkBoxState
            demoInputField.setText(state.text)
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    internal class SavedState : BaseSavedState {

        var radioButtonState: Boolean = false
        var checkBoxState: Boolean = false
        var text: String = ""

        constructor(source: Parcel) : super(source) {
            radioButtonState = source.readByte().toInt() != 0
            checkBoxState = source.readByte().toInt() != 0
            text = source.readString() ?: ""
        }

        constructor(superState: Parcelable) : super(superState)

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeByte((if (radioButtonState) 1 else 0).toByte())
            out.writeByte((if (checkBoxState) 1 else 0).toByte())
            out.writeString(text)
        }

        companion object {
            @JvmField
            val CREATOR = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(source: Parcel): SavedState {
                    return SavedState(source)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}
