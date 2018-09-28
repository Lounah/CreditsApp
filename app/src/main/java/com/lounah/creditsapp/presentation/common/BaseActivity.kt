package com.lounah.creditsapp.presentation.common

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lounah.creditsapp.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected fun showToast(msgId: Int) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show()
    }

    protected fun openFragmentWithBackstack(fragment: BaseFragment) {
        supportFragmentManager?.
                beginTransaction()?.
                add(R.id.container, fragment)?.
                setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)?.
                addToBackStack(fragment.javaClass.name)?.
                commit()
    }

    protected fun openFragmentWithoutBackStack(fragment: Fragment) {
        supportFragmentManager?.
                beginTransaction()?.
                replace(R.id.container, fragment)?.
                commit()
    }

    protected fun openUrl(url: String) {
        val openBrowserIntent = Intent(Intent.ACTION_VIEW)
        openBrowserIntent.data = Uri.parse(url)
        startActivity(openBrowserIntent)

    }
}