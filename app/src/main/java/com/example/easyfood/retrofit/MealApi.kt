package com.example.easyfood.retrofit

import com.example.easyfood.pojo.Mealslist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<Mealslist>




    @GET("lookup.php?")

    fun getMealDetails(@Query("i") id: String) : Call<Mealslist>

   /* https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772*/
}