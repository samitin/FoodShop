package ru.samitin.foodshop.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ru.samitin.foodshop.Adapter.CategoryAdapter
import ru.samitin.foodshop.Adapter.PopularAdapter
import ru.samitin.foodshop.Domain.CategoryDomain
import ru.samitin.foodshop.Domain.FoodDomain
import ru.samitin.foodshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter : CategoryAdapter
    private lateinit var popularAdapter: PopularAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewCategory()
        recyclerViewPopular()
        bottomNavigation()
    }

    private fun recyclerViewCategory() {
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val category = ArrayList<CategoryDomain>()
        category.add(CategoryDomain("Pizza","cat_1"))
        category.add(CategoryDomain("Burger","cat_2"))
        category.add(CategoryDomain("Hotdog","cat_3"))
        category.add(CategoryDomain("Drink","cat_4"))
        category.add(CategoryDomain("Donat","cat_5"))
        categoryAdapter = CategoryAdapter(category)
        binding.recyclerViewCategory.adapter = categoryAdapter
    }
    private fun recyclerViewPopular() {
        binding.recyclerViewPopular.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val food = ArrayList<FoodDomain>()
        food.add(FoodDomain(title="Pepperoni pizza", pic = "pop_1", description = "slices pepperoni,mozzerella cheese,fresh oregano,ground black pepper,pizza souse", free = 9.76))
        food.add(FoodDomain("Cheese Burger","pop_2","beef, Gouda Cheese, Special souse, Lettuce, tomato",8.79))
        food.add(FoodDomain("Vegetable pizza","pop_3","olive oil, Vegetable oil, pitted kalamata, cherry tomatoes, fresh oregano,basil,8.5"))
        popularAdapter = PopularAdapter(food)
        binding.recyclerViewPopular.adapter = popularAdapter
    }
    private fun bottomNavigation(){
        binding.shopBtn.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
        }
        binding.homeBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}