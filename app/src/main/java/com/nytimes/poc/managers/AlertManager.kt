package com.nytimes.poc.managers

import android.app.Activity
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created by Zahid Ali
 */
class AlertManager {
    companion object {

        fun showErrorMessage(activity: Activity, message: String) {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()

        }

        fun showSuccessMessage(activity: Activity, message: String) {


        }

        fun showInfoMessage(activity: Activity, title: String, message: String) {

        }

        fun showToast(activity: Activity, message: String) {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()

        }

        fun showExceptionMessage(activity: Activity, message: String) {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()

        }

        fun twoActionDialog(
            activity: Activity,
            title: String,
            message: String,
            positive: String,
            negative: String,
            click: TwoActionDialogCallBack? = null
        ) {
            MaterialAlertDialogBuilder(activity)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(negative) { dialog, which ->
                    click?.onNegativeClick()
                }
                .setPositiveButton(positive) { dialog, which ->
                    click?.onPositiveClick()
                }
                .show()
        }

        fun singleActionDialog(
            activity: Activity,
            title: String,
            message: String,
            negative: String,
            click: SingleActionDialogCallBack? = null
        ) {
            MaterialAlertDialogBuilder(activity)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(negative) { dialog, which ->
                    click?.onNegativeClick()
                }

                .show()
        }


        fun permissionRequestPopup(activity: Activity) {

        }


        fun showInfoAlertDialog(activity: Activity, title: String, detail: String) {

        }
    }

    interface TwoActionDialogCallBack {
        fun onPositiveClick()
        fun onNegativeClick()
    }

    interface SingleActionDialogCallBack {
        fun onNegativeClick()
    }
}
