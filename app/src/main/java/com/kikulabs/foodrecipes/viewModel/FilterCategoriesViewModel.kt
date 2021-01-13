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
import com.kikulabs.foodrecipes.model.DataRecipes
import org.json.JSONException
import org.json.JSONObject

class FilterCategoriesViewModel : ViewModel() {
    private val link = BuildConfig.BASE_API
    private val listRecipes = MutableLiveData<ArrayList<DataRecipes>>()

    fun setRecipes(strCategory: String) {
        val recipes = ArrayList<DataRecipes>()
        AndroidNetworking.get(link + "filter.php?c={strCategory}")
            .addPathParameter("strCategory", strCategory)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("Success get recipes", "onResponse: $response")
                    run {
                        try {

                            val dataList = response.getJSONArray("meals")
                            for (i in 0 until dataList.length()) {
                                val data = dataList.getJSONObject(i)
                                val recipesItems = DataRecipes()

                                recipesItems.idMeal = data.getString("idMeal")
                                recipesItems.strMeal = data.getString("strMeal")
                                recipesItems.strMealThumb = data.getString("strMealThumb")

                                recipes.add(recipesItems)
                            }

                            listRecipes.postValue(recipes)

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

    fun getRecipes(): LiveData<ArrayList<DataRecipes>> {
        return listRecipes
    }
}