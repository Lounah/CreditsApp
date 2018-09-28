package com.lounah.creditsapp.presentation.creditdetails

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lounah.creditsapp.R
import com.lounah.creditsapp.data.entity.CreditOption

class CreditTypeRvAdapter
    : RecyclerView.Adapter<CreditTypeRvAdapter.ViewHolder>() {

    private val creditTypes = mutableListOf<CreditOption>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_credit_type, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = creditTypes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = creditTypes[position]
        type?.let { holder.bind(it) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val creditType: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_credit_type)
        }

        private val cost: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_cost)
        }

        private val rate: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_rate)
        }

        private val period: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_period)
        }


        fun bind(item: CreditOption) = with(itemView) {
            item.creditType?.let {
                creditType.text = it
            }
            item.cost?.let {
                cost.text = it
            }
            item.period?.let {
                period.text = it
            }
            item.rate?.let {
                rate.text = it
            }
        }
    }


    fun updateDataSet(newOffers: List<CreditOption>) {
        creditTypes.clear()
        creditTypes.addAll(newOffers)
        notifyDataSetChanged()
    }

}