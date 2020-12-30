package com.kikulabs.foodrecipes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kikulabs.foodrecipes.adapter.GridCategoriesAdapter
import com.kikulabs.foodrecipes.adapter.ListRecommendationAdapter
import com.kikulabs.foodrecipes.databinding.ActivityMainBinding
import com.kikulabs.foodrecipes.viewModel.HomeViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listRecommendationAdapter: ListRecommendationAdapter
    private lateinit var gridCategoriesAdapter: GridCategoriesAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initListener()
        initViewModel()
    }

    private fun initView() {

        val myColor: Int = Color.parseColor("#1B8045")
        binding.swLayout.setColorSchemeColors(myColor)

        binding.toolbarHome.collapseToolbar.setExpandedTitleColor(Color.argb(0, 0, 0, 0))
        binding.toolbarHome.collapseToolbar.setContentScrimColor(resources.getColor(R.color.white))

        binding.rvRecommendation.setHasFixedSize(true)
        binding.rvCategories.setHasFixedSize(true)

        listRecommendationAdapter = ListRecommendationAdapter()
        listRecommendationAdapter.notifyDataSetChanged()

        binding.rvRecommendation.layoutManager = LinearLayoutManager(this)
        binding.rvRecommendation.adapter = listRecommendationAdapter


        gridCategoriesAdapter = GridCategoriesAdapter()
        gridCategoriesAdapter.notifyDataSetChanged()

        binding.rvCategories.layoutManager =
            GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding.rvCategories.adapter = gridCategoriesAdapter

    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(HomeViewModel::class.java)

        homeViewModel.getRecommendation().observe(this, Observer { recommendItems ->
            if (recommendItems.isNotEmpty()) {
                listRecommendationAdapter.setData(recommendItems)
                showLoading(false)
            } else {
                showLoading(false)
            }
        })

        homeViewModel.getCategories().observe(this, Observer { categories ->
            if (categories.isNotEmpty()) {
                gridCategoriesAdapter.setData(categories)
                showLoading(false)
            } else {
                showLoading(false)
            }
        })

    }

    private fun initListener() {

        binding.swLayout.post {
            showLoading(true)
            homeViewModel.setRecommendation()
            homeViewModel.setCategories()
        }

        binding.swLayout.setOnRefreshListener {
            showLoading(true)
            homeViewModel.setRecommendation()
            homeViewModel.setCategories()
        }

    }

    private fun showLoading(state: Boolean) {
        binding.swLayout.isRefreshing = state

        if (state){

            binding.shimmerRecommendation.startShimmerAnimation()
            binding.shimmerCategories.startShimmerAnimation()

        }else{

            binding.shimmerRecommendation.stopShimmerAnimation()
            binding.shimmerCategories.stopShimmerAnimation()
            binding.shimmerRecommendation.visibility = View.GONE
            binding.shimmerCategories.visibility = View.GONE

        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

}