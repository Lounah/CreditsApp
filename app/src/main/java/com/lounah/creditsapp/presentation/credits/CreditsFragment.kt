package com.lounah.creditsapp.presentation.credits

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.lounah.creditsapp.R
import com.lounah.creditsapp.domain.model.CreditOfferTitleViewObject
import com.lounah.creditsapp.presentation.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_credits.*
import javax.inject.Inject

class CreditsFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_credits
    override val TAG = "fragment_credits"

    @Inject
    lateinit var viewModel: CreditsViewModel

    override fun initComponents() {
        initCreditGroupsViewPager()
        initCreditGroupsTabLayout()
        initViewModelObservers()

        viewModel.getCreditOffersTitles()
    }

    private fun initViewModelObservers() {
        viewModel.creditOffersTitles.observe(this, Observer {
            it?.let {
                val adapter = CreditGroupsFragmentPagerAdapter(fragmentManager!!)
                adapter.updateDataSet(it)
                viewpager_credits.adapter = adapter
            }
        })

        viewModel.loadingState.observe(this, Observer {
            it?.let {
                if (it) showLoading() else hideLoading()
            }
        })
    }

    private fun initCreditGroupsTabLayout() {
        tl_credit_groups.setupWithViewPager(viewpager_credits)
    }

    private fun initCreditGroupsViewPager() {
        viewpager_credits.offscreenPageLimit = 3
    }

    private fun showLoading() {
        view_creditoffers_loading.visibility = View.VISIBLE
        appbar_credits.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        view_creditoffers_loading.visibility = View.GONE
        appbar_credits.visibility = View.VISIBLE
    }

    inner class CreditGroupsFragmentPagerAdapter(fragmentManager: FragmentManager)
        : FragmentPagerAdapter(fragmentManager) {

        private val creditOfferTitles = mutableListOf<CreditOfferTitleViewObject>()

        override fun getCount() = creditOfferTitles.size

        override fun getPageTitle(position: Int): CharSequence? = creditOfferTitles[position].title

        override fun getItem(position: Int): Fragment? {
            return CreditOffersListFragment.newInstance(creditOfferTitles[position].title!!)
        }

        fun updateDataSet(newOffers: List<CreditOfferTitleViewObject>?) {
            creditOfferTitles.clear()
            creditOfferTitles.addAll(newOffers as MutableList)
            notifyDataSetChanged()
        }
    }

}