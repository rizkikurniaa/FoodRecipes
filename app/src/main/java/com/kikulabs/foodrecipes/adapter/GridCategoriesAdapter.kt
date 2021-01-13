package com.kikulabs.foodrecipes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kikulabs.foodrecipes.R
import com.kikulabs.foodrecipes.activity.FilterRecipesActivity
import com.kikulabs.foodrecipes.databinding.ItemGridCategoriesBinding
import com.kikulabs.foodrecipes.model.DataCategories

class GridCategoriesAdapter : RecyclerView.Adapter<GridCategoriesAdapter.ListViewHolder>() {
    private val dataCategories = ArrayList<DataCategories>()

    fun setData(items: ArrayList<DataCategories>) {
        dataCategories.clear()
        dataCategories.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemGridCategoriesBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_grid_categories, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataCategories.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = dataCategories[position]

        with(holder) {
            binding.tvCategory.text = data.strCategory
            Glide.with(itemView.context)
                .load(data.strCategoryThumb)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivCategory)

            itemView.setOnClickListener {
                val context: Context = it!!.context
                val intent = Intent(context, FilterRecipesActivity::class.java)
                intent.putExtra(FilterRecipesActivity.EXTRA_CATEGORY, data)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }
}