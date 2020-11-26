package com.petnagy.customviewdemo.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import com.google.android.material.card.MaterialCardView

class DemoLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    private var onCheckedChangeListener: OnCheckedChangeListener? = null

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (child is DemoView) {
            child.setOnClickListener {
                setAllButtonsToUnselectedState()
                child.setRadioButton(true)
                onCheckedChangeListener?.onCheckedChanged(child.id)
            }
        } else if (child is MaterialCardView) {
            child.children.forEach { view ->
                if (view is DemoView) {
                    view.setOnClickListener {
                        setAllButtonsToUnselectedState()
                        view.setRadioButton(true)
                        onCheckedChangeListener?.onCheckedChanged(child.id)
                    }
                }
            }
        }
        super.addView(child, params)
    }

    private fun setAllButtonsToUnselectedState() {
        forEachIndexed { index, _ ->
            val child = getChildAt(index)
            if (child is DemoView) {
                child.setRadioButton(false)
            } else if (child is MaterialCardView) {
                child.children.forEach {  view ->
                    if (view is DemoView) {
                        view.setRadioButton(false)
                    }
                }
            }
        }
    }
}

interface OnCheckedChangeListener {
    fun onCheckedChanged(checkedId: Int)
}
