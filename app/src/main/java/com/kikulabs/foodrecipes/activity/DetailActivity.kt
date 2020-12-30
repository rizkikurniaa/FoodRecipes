package com.kikulabs.foodrecipes.activity

import android.os.Bundle
import android.text.Html
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

        val selectedRecipe: DataRecipes? = intent.getParcelableExtra(EXTRA_RECIPE)

        if (selectedRecipe != null) {

            binding.toolbar.tvTitle.text = selectedRecipe.strMeal
            binding.toolbar.tvSubTitle.text = "${selectedRecipe.strArea} | ${selectedRecipe.strCategory}"

        }

    }

    private fun initListener(){
        binding.toolbar.ibBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_back -> {
                onBackPressed()
            }
        }
    }
}