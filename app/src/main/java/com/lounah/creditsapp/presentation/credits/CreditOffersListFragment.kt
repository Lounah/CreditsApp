package com.lounah.creditsapp.presentation.credits

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.lounah.creditsapp.R
import com.lounah.creditsapp.domain.model.CreditOfferViewObject
import com.lounah.creditsapp.presentation.common.BaseFragment
import com.lounah.creditsapp.presentation.creditdetails.CreditDetailsActivity
import kotlinx.android.synthetic.main.fragment_credits_list.*
import javax.inject.Inject

class CreditOffersListFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_credits_list
    override val TAG = "credit_offers_list_fragment"

    @Inject
    lateinit var viewModel: CreditsViewModel

    private lateinit var adapter: CreditOffersRecyclerViewAdapter

    companion object {
        private const val CREDIT_GROUP_NAME_KEY = "CREDIT_GROUP_KEY"
        fun newInstance(creditGroupName: String): BaseFragment {
            val bundle = Bundle().apply { putString(CREDIT_GROUP_NAME_KEY, creditGroupName) }
            return CreditOffersListFragment().apply { arguments = bundle }
        }
    }

    override fun initComponents() {
        initRecyclerView()
        initViewModelObservers()
        arguments?.let {
            if (it[CREDIT_GROUP_NAME_KEY] != null)
                viewModel.getCreditOffersByCreditGroup(it[CREDIT_GROUP_NAME_KEY] as String)
        }
    }

    private fun initViewModelObservers() {
        viewModel.creditOffers.observe(activity!!, Observer {
            it?.let {
                adapter.updateDataSet(it)
            }
        })
    }

    private fun initRecyclerView() {
        adapter = CreditOffersRecyclerViewAdapter(object : CreditOffersRecyclerViewAdapter.OnItemClickedCallback {
            override fun onItemClicked(item: CreditOfferViewObject) {
                val openCreditDetailsActivityIntent = Intent(context, CreditDetailsActivity::class.java)
                openCreditDetailsActivityIntent.putExtra("CREDIT_OFFER_LINK_URL_KEY", item.siteUrl)
                openCreditDetailsActivityIntent.putExtra("CREDIT_OFFER_COMPANY_NAME_KEY", item.companyName)
                startActivity(openCreditDetailsActivityIntent)
            }

        })
        rv_credit_offers.adapter = adapter
        rv_credit_offers.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        rv_credit_offers.layoutManager = LinearLayoutManager(context)
    }

    private fun showLoading() {
        view_creditoffers_loading.visibility = View.VISIBLE
        rv_credit_offers.visibility = View.GONE
    }

    private fun hideLoading() {
        view_creditoffers_loading.visibility = View.GONE
        rv_credit_offers.visibility = View.VISIBLE
    }

}