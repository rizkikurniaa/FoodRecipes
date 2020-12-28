package com.kikulabs.foodrecipes.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.kikulabs.foodrecipes.BuildConfig
import com.kikulabs.foodrecipes.model.DataCategories
import com.kikulabs.foodrecipes.model.DataRecipes
import org.json.JSONException
import org.json.JSONObject

class HomeViewModel: ViewModel() {
    private val link = BuildConfig.BASE_API
    private val listRecommendation = MutableLiveData<ArrayList<DataRecipes>>()
    private val lisCategories = MutableLiveData<ArrayList<DataCategories>>()

    fun setRecommendation() {
        val recommendation = ArrayList<DataRecipes>()
        AndroidNetworking.get(link + "random.php")
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("Success get recommend", "onResponse: $response")
                    run {
                        try {

                            val dataList = response.getJSONArray("meals")
                            for (i in 0 until dataList.length()) {
                                val data = dataList.getJSONObject(i)
                                val recommendItems = DataRecipes()

                                recommendItems.idMeal = data.getString("idMeal")
                                recommendItems.strMeal = data.getString("strMeal")
                                recommendItems.strMealThumb = data.getString("strMealThumb")
                                recommendItems.strArea = data.getString("strArea")
                                recommendItems.strCategory = data.getString("strCategory")

                                recommendation.add(recommendItems)
                            }

                            listRecommendation.postValue(recommendation)

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onError(error: ANError) {
                    Log.d("Error get recommend", "onError: $error")
                }
            })
    }

    fun setCategories() {
        val categories = ArrayList<DataCategories>()
        AndroidNetworking.get(link + "categories.php")
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("Success get categories", "onResponse: $response")
                    run {
                        try {

                            val dataList = response.getJSONArray("categories")
                            for (i in 0 until dataList.length()) {
                                val data = dataList.getJSONObject(i)
                                val categoriesRecipes = DataCategories()

                                categoriesRecipes.strCategory = data.getString("strCategory")
                                categoriesRecipes.strCategoryThumb = data.getString("strCategoryThumb")
                                categoriesRecipes.strCategoryDescription = data.getString("strCategoryDescription")

                                categories.add(categoriesRecipes)
                            }

                            lisCategories.postValue(categories)

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onError(error: ANError) {
                    Log.d("Error get categories", "onError: $error")
                }
            })
    }

    fun getCategories(): LiveData<ArrayList<DataCategories>> {
        return lisCategories
    }

    fun getRecommendation(): LiveData<ArrayList<DataRecipes>> {
        return listRecommendation
    }
}