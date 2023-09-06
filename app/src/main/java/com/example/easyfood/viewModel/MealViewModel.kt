package com.example.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.Mealslist
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel :ViewModel() {

    private  var mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDeatail(id: String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object:Callback<Mealslist>{
            override fun onResponse(call: Call<Mealslist>, response: Response<Mealslist>) {
                if (response.body() != null){
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<Mealslist>, t: Throwable) {
               Log.d("MealActivity",t.message.toString())
            }

        })
    }


    fun observeMealDetailsLiveData(): LiveData<Meal>{
        return mealDetailsLiveData
    }
}