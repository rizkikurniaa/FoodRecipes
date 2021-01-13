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

class DetailViewModel: ViewModel() {
    private val link = BuildConfig.BASE_API
    private val detailRecipe = MutableLiveData<DataRecipes>()

    fun setDetail(idMeal: String) {
        AndroidNetworking.get(link + "lookup.php?i={idMeal}")
            .addPathParameter("idMeal", idMeal)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("Success get detail", "onResponse: $response")
                    run {
                        try {

                            val dataList = response.getJSONArray("meals")
                            for (i in 0 until dataList.length()) {
                                val data = dataList.getJSONObject(i)
                                val detail = DataRecipes()

                                detail.strArea = data.getString("strArea")
                                detail.strCategory = data.getString("strCategory")
                                detail.strInstructions = data.getString("strInstructions")
                                detail.strYoutube = data.getString("strYoutube")
                                detail.strSource = data.getString("strSource")
                                detail.strIngredient = ""
                                detail.strMeasure = ""

                                for (j in 1 until 21) {
                                    if (data.getString("strIngredient$j").trim().isNotEmpty()){
                                        detail.strIngredient += "\n \u2022 " + data.getString("strIngredient$j")
                                    }
                                }

                                for (k in 1 until 21) {
                                    if (data.getString("strMeasure$k").trim().isNotEmpty()){
                                        detail.strMeasure += "\n : " + data.getString("strMeasure$k")
                                    }
                                }

                                detailRecipe.postValue(detail)

                            }

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onError(error: ANError) {
                    Log.d("Error get detail", "onError: $error")
                }
            })
    }

    fun getDetail(): LiveData<DataRecipes> {
        return detailRecipe
    }
}