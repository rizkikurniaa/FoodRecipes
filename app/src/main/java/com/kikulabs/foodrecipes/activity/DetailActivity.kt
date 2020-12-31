package com.kikulabs.foodrecipes.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kikulabs.foodrecipes.R
import com.kikulabs.foodrecipes.databinding.ActivityDetailBinding
import com.kikulabs.foodrecipes.model.DataRecipes

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_RECIPE = "extra_recipe"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()
    }

    private fun initView() {
        //handle color toolbar when scroll up
        binding.collapseToolbar.setExpandedTitleColor(Color.argb(0, 0, 0, 0))
        binding.collapseToolbar.setContentScrimColor(resources.getColor(R.color.white))
        //added icon to navigation
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back)

        val selectedRecipe: DataRecipes? = intent.getParcelableExtra(EXTRA_RECIPE)

        if (selectedRecipe != null) {

            binding.tvMeal.text = selectedRecipe.strMeal
            binding.tvSubMeal.text = "${selectedRecipe.strArea} | ${selectedRecipe.strCategory}"

            Glide.with(this)
                .load(selectedRecipe.strMealThumb)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivBgMeal)

            Glide.with(this)
                .load(selectedRecipe.strMealThumb)
                .circleCrop()
                .placeholder(R.drawable.placeholder)
                .into(binding.ivMeal)

        }

    }

    private fun initListener() {
        binding.toolbar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.toolbar -> {
                onBackPressed()
            }
        }
    }
}