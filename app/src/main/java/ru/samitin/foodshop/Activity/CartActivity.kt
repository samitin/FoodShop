package ru.samitin.foodshop.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.samitin.foodshop.Adapter.CartAdapter
import ru.samitin.foodshop.Helper.ManagementCart
import ru.samitin.foodshop.Interface.ChangeNumberItemsListener
import ru.samitin.foodshop.R
import ru.samitin.foodshop.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private var tax : Double = 0.0
    private lateinit var adapter: CartAdapter
    private lateinit var managementCart: ManagementCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managementCart = ManagementCart(this)
        initList()
        calculate()
        bottomNavigation()
    }
    private fun bottomNavigation(){
        binding.shopBtn.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
        }
        binding.homeBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun initList() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter = CartAdapter(managementCart.listCart,managementCart,object :ChangeNumberItemsListener{
            override fun changed() {
                calculate()
            }
        })
        binding.recyclerView.adapter = adapter
        if (managementCart.listCart.isEmpty()){
            binding.emptyText.visibility = View.VISIBLE
            binding.scrollView.visibility = View.GONE
        }else{
            binding.emptyText.visibility = View.GONE
            binding.scrollView.visibility = View.VISIBLE
        }
    }
    private fun calculate(){
        val percentTax = 0.02
        val delivery = 10
        tax = ((Math.round(managementCart.totalFee * percentTax) * 100).toDouble())/100.0
        val total = (Math.round(managementCart.totalFee + tax +delivery)*100).toDouble()/100.0
        val itemTotal =(Math.round(managementCart.totalFee *100).toDouble()/100.0)
        binding.totalText.text="$ "+total.toString()
        binding.taxText.text = "$ "+ tax.toString()
        binding.deliveryText.text="$ "+delivery.toString()
        binding.totalText.text = "$ "+total.toString()
        binding.totalFeeText.text="$ "+itemTotal

    }
}