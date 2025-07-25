package com.example.ecommerceappshoes.Adapter


import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceappshoes.Model.BrandModel
import com.example.ecommerceappshoes.R
import com.example.ecommerceappshoes.databinding.ViewholderBrandBinding


class BrandsAdapter(private val items:MutableList<BrandModel>) :
    RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

        private var selectedPosition=-1
        private var lastSelectedPosition=-1

        fun updateData(newData: List<BrandModel>){
            items.clear()
            items.addAll(elements = newData)
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ViewholderBrandBinding):
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BrandsAdapter.ViewHolder {
        val binding= ViewholderBrandBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
            return ViewHolder(binding)
       }

    override fun onBindViewHolder(holder: BrandsAdapter.ViewHolder,position:Int) {
        val item = items[position]

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            if (selectedPosition != position) {
                lastSelectedPosition = selectedPosition
                selectedPosition = position
                if (lastSelectedPosition != -1) notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
        }

        val isSelected = selectedPosition == position

        holder.binding.pic.setBackgroundResource(
            if (isSelected) 0 else R.drawable.grey_full_corner
        )

        ImageViewCompat.setImageTintList(
            holder.binding.pic,
            ColorStateList.valueOf(
                holder.itemView.context.getColor(
                    if (isSelected) R.color.white else R.color.black
                )
            )
        )
    }



    override fun getItemCount()= items.size
}

