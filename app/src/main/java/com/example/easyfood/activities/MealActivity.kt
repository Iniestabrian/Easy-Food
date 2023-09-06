package com.example.easyfood.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.fragments.HomeFragment
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {


    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String

    private lateinit var binding: ActivityMealBinding
    private  lateinit var youtubeLink: String

    private  lateinit var mealMvvm:MealViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProvider(this)[MealViewModel::class.java]

        getMealInfoFromIntent()
        setInfoInViews()
        loading()
        mealMvvm.getMealDeatail(mealId)
        observeMealDeatailsLiveData()

        onYoutubeImageClick()





    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observeMealDeatailsLiveData() {
        mealMvvm.observeMealDetailsLiveData().observe(this,object :Observer<Meal>{
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val meal = t

                binding.tvCategory.text = "Categoty : ${meal!!.strCategory}"
                binding.tvArea.text = "Area : ${meal.strArea}"
                binding.tvInstructionSteps.text = meal.strInstructions

                youtubeLink = meal.strYoutube
            }

        })

    }

    private fun setInfoInViews() {
      Glide.with(applicationContext)
          .load(mealThumb)
          .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

    }

    private fun getMealInfoFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!

    }

    private  fun loading(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddToFavs.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE

    }
    private  fun onResponseCase(){


        binding.apply {
            progressBar.visibility = View.INVISIBLE
            btnAddToFavs.visibility = View.VISIBLE
            tvInstructions.visibility = View.VISIBLE
            tvCategory.visibility = View.VISIBLE
            tvArea.visibility = View.VISIBLE
            imgYoutube.visibility = View.VISIBLE
        }

    }
}