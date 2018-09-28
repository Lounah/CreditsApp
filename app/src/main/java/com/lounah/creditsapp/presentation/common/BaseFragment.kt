package com.lounah.creditsapp.presentation.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lounah.creditsapp.R
import dagger.android.support.AndroidSupportInjection


abstract class BaseFragment : Fragment() {

    protected abstract val layoutRes: Int
    protected abstract val TAG: String

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    protected fun showToast(msgId: Int) {
        Toast.makeText(context, msgId, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun openFragmentWithBackstack(fragment: BaseFragment) {
        activity?.supportFragmentManager?.
                beginTransaction()?.
                add(R.id.container, fragment)?.
                setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)?.
                addToBackStack(fragment.TAG)?.
                commit()
    }

    protected fun openFragmentWithoutBackStack(fragment: Fragment) {
        activity?.supportFragmentManager?.
                beginTransaction()?.
                replace(R.id.container, fragment)?.
                commit()
    }

    protected fun openUrl(url: String) {
        val openBrowserIntent = Intent(Intent.ACTION_VIEW)
        openBrowserIntent.data = Uri.parse(url)
        startActivity(openBrowserIntent)

    }

    protected abstract fun initComponents()
}
