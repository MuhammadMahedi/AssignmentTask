package com.example.assignmenttask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmenttask.R
import com.example.assignmenttask.models.All

class MembersAdapter(private val context: Context,
                     private val list:List<All>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_member,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if(holder is MyViewHolder){
            holder.tvName.text = model.firstName
            holder.tvPosition.text = model.position.toString()
            holder.tvLevel.text = model.level.toString()
            holder.tvGiftCoin.text = ((model.giftcoin)/1000.0).toString()+"K"

            if(model.gender.isNotEmpty()){
                holder.tvGander.text = model.gender
            }else holder.tvGander.text = "male"

            Glide
                .with(context)
                .load(model.profilePic)
                .centerCrop()
                .placeholder(R.color.black)
                .dontAnimate()
                .into(holder.image)

        }
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvName = itemView.findViewById<TextView>(R.id.name)
        val tvPosition = itemView.findViewById<TextView>(R.id.tv_position)
        val tvLevel = itemView.findViewById<TextView>(R.id.tv_level)
        val tvGiftCoin = itemView.findViewById<TextView>(R.id.tv_gift_coin)
        val tvGander = itemView.findViewById<TextView>(R.id.tv_gander)
        val image= itemView.findViewById<ImageView>(R.id.img)
    }
}