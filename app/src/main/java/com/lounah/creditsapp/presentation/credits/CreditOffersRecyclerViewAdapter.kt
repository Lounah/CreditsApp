package com.lounah.creditsapp.presentation.credits

import android.support.v7.widget.RecyclerView
import com.lounah.creditsapp.domain.model.CreditOfferViewObject
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lounah.creditsapp.R


class CreditOffersRecyclerViewAdapter(private val onItemClickedCallback: OnItemClickedCallback)
    : RecyclerView.Adapter<CreditOffersRecyclerViewAdapter.ViewHolder>() {

    private val creditOffers = mutableListOf<CreditOfferViewObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_creditoffer, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = creditOffers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offer = creditOffers[position]
        holder.itemView.setOnClickListener { onItemClickedCallback.onItemClicked(offer) }
        holder.bind(offer)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val icon: ImageView by lazy {
            itemView.findViewById<ImageView>(R.id.iv_creditoffer)
        }

        private val description: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_creditoffer_description)
        }

        private val name: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_creditoffer_company_name)
        }

        fun bind(item: CreditOfferViewObject) = with(itemView) {

            description.text = item.description

            name.text = item.companyName

            Glide.with(this)
                    .load(item.imageUrl)
                    .apply(RequestOptions().placeholder(R.drawable.all_image_placeholder))
                    .into(icon)
        }
    }


    fun updateDataSet(newOffers: List<CreditOfferViewObject>) {

        val diffCallback = CreditOfferDiffUtilCallback(creditOffers, newOffers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        creditOffers.clear()
        creditOffers.addAll(newOffers)

        diffResult.dispatchUpdatesTo(this)
    }

    interface OnItemClickedCallback {
        fun onItemClicked(item: CreditOfferViewObject)
    }
}