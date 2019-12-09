package com.android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.android.R
import com.android.model.Facts
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_each_facts.view.*

class FactsAdapter(private val mFactsList: List<Facts>?) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_each_facts, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int {
        return if (mFactsList != null && mFactsList.size > 0) {
            mFactsList.size
        } else {
            0
        }
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val ivThumbNail: ImageView = itemView.ivThumbnail
        private val tvTitle: TextView = itemView.tvTitle
        private val tvDescription: TextView = itemView.tvDescription
        override fun clear() {
            ivThumbNail.setImageDrawable(null)
            tvTitle.text = ""
        }

        override fun onBind(position: Int) {
            super.onBind(position)
            val mFacts = mFactsList!![position]
            if (mFacts.imageHref != null) {
                Glide.with(itemView.context)
                        .load(mFacts.imageHref)
                        .placeholder(R.drawable.ic_error)
                        .error(R.drawable.ic_error)
                        .into(ivThumbNail
                        )
            }
            if (mFacts.title != null) {
                tvTitle.text = mFacts.title
            }
            if (mFacts.description != null) {
                tvDescription.text = mFacts.description
            }
        }
    }

    companion object {
        private val TAG = "FactsAdapter"
    }

}
