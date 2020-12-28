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

class HomeViewModel: ViewModel() {
    private val link = BuildConfig.BASE_API_RANDOM
    private val listRecommendation = MutableLiveData<ArrayList<DataRecipes>>()

    fun setRecommendation() {
        val recommendation = ArrayList<DataRecipes>()

        AndroidNetworking.get(link)
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

                                recommendItems.strMeal = data.getString("strMeal")
                                recommendItems.strMealThumb = data.getString("strMealThumb")

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


    fun getRecommendation(): LiveData<ArrayList<DataRecipes>> {
        return listRecommendation
    }
}