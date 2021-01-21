package com.kikulabs.foodrecipes.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.kikulabs.foodrecipes.R
import com.kikulabs.foodrecipes.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener, View.OnClickListener {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()

    }

    private fun initView(){
        val searchPlateId: Int = binding.toolbarSearch.svProduk.context.resources
            .getIdentifier("android:id/search_plate", null, null)
        val searchPlate: View =
            binding.toolbarSearch.svProduk.findViewById(searchPlateId)
        searchPlate?.setBackgroundColor(Color.TRANSPARENT)

        val linearLayout1 = binding.toolbarSearch.svProduk.getChildAt(0) as LinearLayout
        val linearLayout2 = linearLayout1.getChildAt(2) as LinearLayout
        val linearLayout3 = linearLayout2.getChildAt(1) as LinearLayout
        val autoComplete = linearLayout3.getChildAt(0) as AutoCompleteTextView
        autoComplete.textSize = 14f

        binding.toolbarSearch.svProduk.setOnQueryTextListener(this)
        binding.toolbarSearch.svProduk.isFocusable = false
    }

    private fun initListener(){
        binding.toolbarSearch.ibBack.setOnClickListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_back -> {
                onBackPressed()
            }
        }
    }
}