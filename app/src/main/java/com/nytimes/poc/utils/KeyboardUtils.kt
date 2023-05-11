package com.nytimes.poc.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference

/**
 * Created by Zahid Ali
 */
object KeyboardUtils {
    fun hideKeyboard(activity: Activity, view: View?) {
//        val view = activity.findViewById<View>(R.id.content)
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hideKeyBoardForce(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun showKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun addKeyboardVisibilityListener(
        rootLayout: View,
        onKeyboardVisibiltyListener: OnKeyboardVisibiltyListener
    ) {
        rootLayout.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            rootLayout.getWindowVisibleDisplayFrame(r)
            val screenHeight = rootLayout.rootView.height

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            val keypadHeight = screenHeight - r.bottom
            val isVisible =
                keypadHeight > screenHeight * 0.15 // 0.15 ratio is perhaps enough to determine keypad height.
            onKeyboardVisibiltyListener.onVisibilityChange(isVisible)
        }
    }

    interface OnKeyboardVisibiltyListener {
        fun onVisibilityChange(isVisible: Boolean)
    }


    @SuppressLint("ClickableViewAccessibility")
    fun activateHideKeyboardUponTouchingScreen(view: View, activity: FragmentActivity) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                closeKeyboard(activity)
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                activateHideKeyboardUponTouchingScreen(innerView, activity)
            }
        }
    }

    fun closeKeyboard(activity: FragmentActivity) {
        try {
            val context = WeakReference(activity)
            if (context.get() != null) {
                //receiving current window focus
                var view: View? = context.get()!!.currentFocus
                if (view == null)
                //create view object
                    view = View(context.get()!!)
                //hide keyboard
                val imm =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
                imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}