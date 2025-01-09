package com.phycaresolutions.demoapp_2025

import android.R.attr.phoneNumber
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val status = isNetworkAvailable(context)
        showLog("" + status)
        /*if (callback != null) {
            callback.onNetworkChanged(status)
        }*/
        try {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

            if (state == TelephonyManager.EXTRA_STATE_RINGING && phoneNumber != null) {
                Toast.makeText(
                    context,
                    "Ringing State Number is - " + phoneNumber,
                    Toast.LENGTH_SHORT
                ).show()

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    private fun isNetworkAvailable(context: Context): Boolean {
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = cm.activeNetworkInfo
            return (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting)
        } catch (e: NullPointerException) {
            showLog(e.localizedMessage)
            return false
        }
    }

    private fun showLog(message: String) {
        Log.e("NetworkChangeReceiver", "" + message)
    }
}