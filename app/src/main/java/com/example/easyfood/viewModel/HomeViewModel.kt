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

class HomeViewModel(): ViewModel() {
   private var randomMealLiveData: MutableLiveData<Meal> = MutableLiveData()


    fun getRandomMeal(){

        RetrofitInstance.api.getRandomMeal().enqueue(object  : Callback<Mealslist> {
            override fun onResponse(call: Call<Mealslist>, response: Response<Mealslist>) {


                if(response.body() !=null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    //Log.d("TEST","meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")

                    randomMealLiveData.value = randomMeal

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<Mealslist>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }

        })

    }


    fun observeRandomMealLiveData():LiveData<Meal>{
        return randomMealLiveData
    }
}
