package ru.samitin.foodshop.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.samitin.foodshop.Activity.ShowDetailActivity
import ru.samitin.foodshop.Domain.FoodDomain
import ru.samitin.foodshop.R

class PopularAdapter(private var foodDomains : ArrayList<FoodDomain>) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_popular,parent,false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text =foodDomains.get(position).title
        holder.free.text = foodDomains.get(position).free.toString()
        var  drawableResId  = holder.itemView.context.resources.getIdentifier(foodDomains.get(position).pic,"drawable",holder.itemView.context.packageName)
        Glide.with(holder.itemView.context).load(drawableResId).into(holder.pic)
        holder.addBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context,ShowDetailActivity::class.java)
            intent.putExtra("foodDomain",foodDomains.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return foodDomains.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title : TextView
        var free : TextView
        var pic : ImageView
        var addBtn : TextView
        init {
            title = itemView.findViewById(R.id.title)
            free = itemView.findViewById(R.id.free)
            pic = itemView.findViewById(R.id.pic)
            addBtn = itemView.findViewById(R.id.addBtn)
        }
    }
}