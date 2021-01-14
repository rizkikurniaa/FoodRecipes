package com.kikulabs.foodrecipes.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kikulabs.foodrecipes.R
import com.kikulabs.foodrecipes.activity.DetailActivity
import com.kikulabs.foodrecipes.databinding.ItemRowRecipesBinding
import com.kikulabs.foodrecipes.model.DataRecipes

class ListFilteredRecipesAdapter :
    RecyclerView.Adapter<ListFilteredRecipesAdapter.ListViewHolder>() {
    private val dataFiltered = ArrayList<DataRecipes>()

    fun setData(items: ArrayList<DataRecipes>) {
        dataFiltered.clear()
        dataFiltered.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemRowRecipesBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_recipes, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataFiltered.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = dataFiltered[position]

        with(holder) {

            val colors = arrayOf(
                Color.parseColor("#FF9AA2"),
                Color.parseColor("#FFB7B2"),
                Color.parseColor("#FFDAC1"),
                Color.parseColor("#E2F0CB"),
                Color.parseColor("#B5EAD7"),
                Color.parseColor("#C7CEEA"),
                Color.parseColor("#E7E6CE")
            )
            val randomColor = colors.random()
            binding.cvRecipe.setCardBackgroundColor(randomColor)

            binding.tvRecipe.text = data.strMeal
            Glide.with(itemView.context)
                .load(data.strMealThumb)
                .circleCrop()
                .placeholder(R.drawable.placeholder)
                .into(binding.ivRecipe)

            itemView.setOnClickListener {
                val context: Context = it!!.context
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_RECIPE, data)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)

            }
        }
    }

}