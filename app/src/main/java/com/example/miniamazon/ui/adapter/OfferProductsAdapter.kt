package com.example.miniamazon.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniamazon.data.classes.Product
import com.example.miniamazon.databinding.ItemSpecialOffersLayoutBinding

class OfferProductsAdapter : RecyclerView.Adapter<OfferProductsAdapter.OfferProductViewHolder>() {
    inner class OfferProductViewHolder(
        private val binding: ItemSpecialOffersLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            binding.apply {
                Glide.with(itemView)
                    .load(product.images[0])
                    .into(imageSpecialProduct)
                tvSpecialProductName.text = product.name
                product.offerPercentage?.let {
                    var newPrice = 1f - it
                    newPrice *= product.price
                    tvSpecialProductPrice.text = "$ ${String.format("%.2f", newPrice)}"
                    tvSpecialProductOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                if (product.offerPercentage == null) {
                    tvSpecialProductPrice.visibility = View.GONE
                    tvSpecialProductOldPrice.alpha = 1f
                    tvSpecialProductOldPrice.textSize = 13f
                }
                tvSpecialProductOldPrice.text = "$ ${product.price}"
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferProductViewHolder =
        OfferProductViewHolder(
            ItemSpecialOffersLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: OfferProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }
}