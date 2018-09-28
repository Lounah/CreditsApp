package com.lounah.creditsapp.presentation.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.google.firebase.firestore.FirebaseFirestore
import com.lounah.creditsapp.R
import com.lounah.creditsapp.presentation.common.BaseActivity
import com.lounah.creditsapp.presentation.credits.CreditsFragment
import com.lounah.creditsapp.presentation.history.CreditsHistoryFragment
import com.lounah.creditsapp.presentation.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.yandex.metrica.impl.ob.db


class MainActivity : BaseActivity() {

    private val CREDITS_POSITION = 0
    private val CREDITS_HISTORY_POSITION = 1
    private val SETTINGS_POSITION = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPaymentInfo()

        if (savedInstanceState == null) {
            initBottomNavigation()
        }

    }

    private fun showPaymentDialog() {
        payment_dialog.visibility = View.VISIBLE
        container.visibility = View.GONE
    }

    private fun checkPaymentInfo() {
        var result = true
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("payment_info")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            if (document.data["proceed"].toString() != "true") {
                                showPaymentDialog()
                                result = false
                            }
                        }
                    } else {
                        result = true
                    }
                }
    }

    private fun initBottomNavigation() {
        val creditsItem = AHBottomNavigationItem(resources.getString(R.string.credits), R.drawable.ic_credits_24dp)
        val borrowItem = AHBottomNavigationItem(resources.getString(R.string.borrow), R.drawable.ic_borrow)
        val creditsHistoryItem = AHBottomNavigationItem(resources.getString(R.string.credits_history), R.drawable.ic_history_24dp)
        val settingsItem = AHBottomNavigationItem(resources.getString(R.string.settings), R.drawable.ic_settings_24dp)

        bottom_navigation.addItems(listOf(creditsItem, borrowItem, creditsHistoryItem, settingsItem))

//        creditsItem.setColorRes(R.color.red)
//        borrowItem.setColorRes(R.color.redDark)
//        creditsHistoryItem.setColorRes(R.color.green)
//        settingsItem.setColorRes(R.color.blue)

        bottom_navigation.defaultBackgroundColor = ContextCompat.getColor(this, R.color.colorPrimary)
        bottom_navigation.accentColor = ContextCompat.getColor(this, R.color.colorAccent)

        bottom_navigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW

        bottom_navigation.isColored = false

        bottom_navigation.setOnTabSelectedListener({ position, wasSelected ->
            when (position) {
                CREDITS_POSITION -> openFragmentWithoutBackStack(CreditsFragment())
                CREDITS_HISTORY_POSITION -> if (!wasSelected) openFragmentWithoutBackStack(CreditsHistoryFragment())
                SETTINGS_POSITION -> if (!wasSelected) openFragmentWithoutBackStack(SettingsFragment())
            }
            true
        })

        bottom_navigation.setCurrentItem(CREDITS_POSITION, true)
    }
}
