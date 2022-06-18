package ru.samitin.foodshop.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import ru.samitin.foodshop.Domain.FoodDomain
import ru.samitin.foodshop.Helper.ManagementCart
import ru.samitin.foodshop.R
import ru.samitin.foodshop.databinding.ActivityShowDetailBinding

class ShowDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowDetailBinding
    private lateinit var foodDomain : FoodDomain
    private var numberOrder =1
    private lateinit var managementCart: ManagementCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managementCart = ManagementCart(this)
        getBundle()
    }
    private fun getBundle(){
        foodDomain =intent.getParcelableExtra<FoodDomain>("foodDomain") as FoodDomain

        val drawableResourceId = resources.getIdentifier(foodDomain.pic,"drawable",packageName)
        Glide.with(this).load(drawableResourceId).into(binding.picFood)
        binding.titleText.text = foodDomain.title
        binding.priceText.text = "$ ${foodDomain.free.toString()}"
        binding.descriptionTxt.text = foodDomain.description
        binding.numberOrderText.text = numberOrder.toString()
        binding.plusBtn.setOnClickListener {
            numberOrder++
            binding.numberOrderText.text = numberOrder.toString()
        }
        binding.minusBtn.setOnClickListener {
            if (numberOrder>1)
                numberOrder--
            binding.numberOrderText.text = numberOrder.toString()
        }
        binding.addToCartBtn.setOnClickListener {
            foodDomain.numberInCart = numberOrder
            managementCart.insertFood(foodDomain)
        }
    }

}