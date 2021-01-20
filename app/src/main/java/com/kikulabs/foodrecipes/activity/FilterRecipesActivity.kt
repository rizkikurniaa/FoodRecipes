package com.kikulabs.foodrecipes.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kikulabs.foodrecipes.R
import com.kikulabs.foodrecipes.adapter.ListFilteredRecipesAdapter
import com.kikulabs.foodrecipes.databinding.ActivityFilterRecipesBinding
import com.kikulabs.foodrecipes.model.DataCategories
import com.kikulabs.foodrecipes.viewModel.FilterCategoriesViewModel

class FilterRecipesActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var binding: ActivityFilterRecipesBinding
    private lateinit var filterCategoriesViewModel: FilterCategoriesViewModel
    private lateinit var listFilteredRecipesAdapter: ListFilteredRecipesAdapter
    private lateinit var strCategory: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
        initListener()
    }

    private fun initView() {
        val selectedCategory: DataCategories? = intent.getParcelableExtra(EXTRA_CATEGORY)

        if (selectedCategory != null) {
            strCategory = selectedCategory.strCategory.toString()
            binding.toolbarMenu.tvTitle.text = selectedCategory.strCategory
        }

        binding.rvRecipes.setHasFixedSize(true)

        listFilteredRecipesAdapter = ListFilteredRecipesAdapter()
        listFilteredRecipesAdapter.notifyDataSetChanged()

        binding.rvRecipes.layoutManager = LinearLayoutManager(this)
        binding.rvRecipes.adapter = listFilteredRecipesAdapter

    }

    private fun initViewModel() {
        filterCategoriesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FilterCategoriesViewModel::class.java)

        filterCategoriesViewModel.getRecipes().observe(this, Observer { recipes ->
            if (recipes.isNotEmpty()) {
                listFilteredRecipesAdapter.setData(recipes)
            }
        })

    }

    private fun initListener() {
        filterCategoriesViewModel.setRecipes(strCategory)
        binding.toolbarMenu.ibBack.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_back -> {
                onBackPressed()
            }
        }
    }

}