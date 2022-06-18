package ru.samitin.foodshop.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.samitin.foodshop.Domain.FoodDomain
import ru.samitin.foodshop.Helper.ManagementCart
import ru.samitin.foodshop.Interface.ChangeNumberItemsListener
import ru.samitin.foodshop.R

class CartAdapter(private var foodDomains:ArrayList<FoodDomain>,
                  private var menegementCart: ManagementCart,
                  private var changeNumberItemsListener: ChangeNumberItemsListener) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = foodDomains.get(position).title
        holder.feeEachItem.text = foodDomains.get(position).free.toString()
        holder.totalEachItem.text = ((foodDomains.get(position).numberInCart * foodDomains.get(position).free *100)/100).toString()
        holder.num.text = foodDomains.get(position).numberInCart.toString()
        val drawable = holder.itemView.context.resources.getIdentifier(
            foodDomains.get(position).pic,"drawable",holder.itemView.context.packageName)
        Glide.with(holder.itemView.context).load(drawable).into(holder.pic)
        holder.minusItem.setOnClickListener {
            menegementCart.minusNumberFood(foodDomains,position,object :ChangeNumberItemsListener{
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.changed()
                }
            })
        }
        holder.plusItem.setOnClickListener {
            menegementCart.plusNumberFood(foodDomains,position,object :ChangeNumberItemsListener{
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.changed()
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return foodDomains.size
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val title :TextView
        val feeEachItem :TextView
        val totalEachItem :TextView
        val num :TextView
        val plusItem :ImageView
        val pic :ImageView
        val minusItem :ImageView
        init {
            title=itemView.findViewById(R.id.titleTxt)
            feeEachItem=itemView.findViewById(R.id.feeEachItem)
            totalEachItem=itemView.findViewById(R.id.totalEachItem)
            num=itemView.findViewById(R.id.numberItemTxt)
            plusItem=itemView.findViewById(R.id.plusCardBtn)
            minusItem=itemView.findViewById(R.id.minusCartBtn)
            pic=itemView.findViewById(R.id.picCart)
        }
    }
}