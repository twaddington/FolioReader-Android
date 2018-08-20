package com.folioreader.view

import android.content.Context
import android.graphics.Rect
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.Log

class FolioAppBarLayout : AppBarLayout {

    companion object {
        @JvmField
        val LOG_TAG: String = FolioAppBarLayout::class.java.simpleName
    }

    var navigationBarHeight: Int = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
            Log.d(LOG_TAG, "-> onApplyWindowInsets")
            // For API level 20 and above

            navigationBarHeight = insets.systemWindowInsetBottom

            setMargins(insets.systemWindowInsetLeft, insets.systemWindowInsetTop,
                    insets.systemWindowInsetRight)
            insets
        }
    }

    override fun fitSystemWindows(insets: Rect?): Boolean {
        Log.d(LOG_TAG, "-> fitSystemWindows")
        // For API level 19 and below

        navigationBarHeight = insets!!.bottom

        setMargins(insets.left, insets.top, insets.right)
        return super.fitSystemWindows(insets)
    }

    private fun setMargins(left: Int, top: Int, right: Int) {

        val marginLayoutParams = layoutParams as MarginLayoutParams
        marginLayoutParams.leftMargin = left
        marginLayoutParams.topMargin = top
        marginLayoutParams.rightMargin = right
        layoutParams = marginLayoutParams
    }

    fun setTopMargin(top: Int) {
        val marginLayoutParams = layoutParams as MarginLayoutParams
        marginLayoutParams.topMargin = top
        layoutParams = marginLayoutParams
    }
}