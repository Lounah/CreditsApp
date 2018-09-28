package com.lounah.creditsapp.presentation.creditdetails

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lounah.creditsapp.R
import com.lounah.creditsapp.presentation.common.BaseActivity
import com.lounah.creditsapp.presentation.credits.CreditsViewModel
import kotlinx.android.synthetic.main.content_scrolling_credit_details.*
import kotlinx.android.synthetic.main.fragment_credit_details.*
import javax.inject.Inject



class CreditDetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: CreditsViewModel

    private lateinit var toolbarTitle: String
    private var descriptionWasExpanded = false

    private lateinit var creditTypesRvAdapter: CreditTypeRvAdapter

    companion object {
        private const val CREDIT_OFFER_LINK_URL_KEY = "CREDIT_OFFER_LINK_URL_KEY"
        private const val CREDIT_OFFER_COMPANY_NAME_KEY = "CREDIT_OFFER_COMPANY_NAME_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_credit_details)
        initUI()
        initViewModelObservers()
        intent.extras?.let {
            viewModel.getCreditOfferByCreditOfferURL(it[CREDIT_OFFER_LINK_URL_KEY] as String)
            toolbarTitle = it[CREDIT_OFFER_COMPANY_NAME_KEY] as String
        }
    }

    private fun initUI() {
        initCreditOptionsRecyclerView()
        btn_expand_description.setOnClickListener {
            if (!descriptionWasExpanded) {
                tv_creditofferdetails_description.maxLines = 100
                btn_expand_description.text = resources.getString(R.string.hide)
            } else {
                tv_creditofferdetails_description.maxLines = 5
                btn_expand_description.text = resources.getString(R.string.expand_description)
            }
            descriptionWasExpanded = !descriptionWasExpanded
        }
        details_toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_black_24dp)
        details_toolbar.setNavigationOnClickListener { onBackPressed() }
        details_app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset <= (-appBarLayout.totalScrollRange + 30f)) {
                details_toolbar.title = toolbarTitle
            } else {
                details_toolbar_layout.title = ""
            }
        })
    }

    private fun initCreditOptionsRecyclerView() {
        creditTypesRvAdapter = CreditTypeRvAdapter()
        rv_credit_types.layoutManager = LinearLayoutManager(this)
        rv_credit_types.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rv_credit_types.adapter = creditTypesRvAdapter
    }

    private fun initViewModelObservers() {

        showLoading()

        viewModel.creditOffer.observe(this, Observer {
            it?.let {
                val url = it.siteUrl

                Glide.with(this)
                        .load(it.imageUrl)
                        .apply(RequestOptions().placeholder(R.drawable.all_image_placeholder).fitCenter())
                        .into(iv_details_main)

                it.description?.let {
                    if (it.isEmpty()) {
                        tv_creditofferdetails_description.text = "Отсутствует"
                    } else {
                        tv_creditofferdetails_description.text = it
                    }
                }

                it.advancedInfo?.let {
                    if (it.isEmpty()) {
                        tv_creditofferdetails_advanced_info.text = "Отсутствует"
                    } else {
                        tv_creditofferdetails_advanced_info.text = it
                    }
                }

                it.creditOptions?.let { creditTypesRvAdapter.updateDataSet(it) }

                btn_creditdetails_apply.setOnClickListener { openUrl(url!!) }
                fab_call.setOnClickListener { openUrl(url!!) }

                hideLoading()
            }
        })

    }


    private fun showLoading() {
        view_creditdetails_loading.visibility = View.VISIBLE
        layout_content_scrolling.visibility = View.GONE
        btn_creditdetails_apply.visibility = View.GONE
    }

    private fun hideLoading() {
        view_creditdetails_loading.visibility = View.GONE
        layout_content_scrolling.visibility = View.VISIBLE
        btn_creditdetails_apply.visibility = View.VISIBLE
    }
}