package com.kikulabs.foodrecipes.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kikulabs.foodrecipes.R
import com.kikulabs.foodrecipes.databinding.ActivityDetailBinding
import com.kikulabs.foodrecipes.model.DataRecipes
import com.kikulabs.foodrecipes.viewModel.DetailViewModel

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_RECIPE = "extra_recipe"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var idMeal: String
    private lateinit var youtubeLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
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
            idMeal = selectedRecipe.idMeal.toString()
            binding.tvMeal.text = selectedRecipe.strMeal

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

    private fun initViewModel() {
        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        detailViewModel.getDetail().observe(this, Observer { detail ->
            if (detail != null) {
                binding.tvSubMeal.text = "${detail.strArea} | ${detail.strCategory}"
                youtubeLink = detail.strYoutube.toString()
                binding.tvIngredients.text = detail.strIngredient
                binding.tvMeasures.text = detail.strMeasure
                binding.jtvInstructions.text = detail.strInstructions
                showLoading(false)
            } else {
                showLoading(false)
            }
        })

    }

    private fun initListener() {
        binding.swLayout.post {
            showLoading(true)
            detailViewModel.setDetail(idMeal)
        }

        binding.swLayout.setOnRefreshListener {
            showLoading(true)
            detailViewModel.setDetail(idMeal)
        }

        binding.toolbar.setOnClickListener(this)
        binding.llYoutube.setOnClickListener(this)
    }

    private fun showLoading(state: Boolean) {
        binding.swLayout.isRefreshing = state
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar -> {
                onBackPressed()
            }
            R.id.ll_youtube -> {
                val intentYoutube = Intent(Intent.ACTION_VIEW)
                intentYoutube.data = Uri.parse(youtubeLink)
                startActivity(intentYoutube)
            }
        }
    }
}