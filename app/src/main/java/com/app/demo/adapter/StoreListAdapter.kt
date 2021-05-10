package com.app.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.demo.R
import com.app.demo.data.StoreDataResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.store_row_item.view.*


class StoreListAdapter(val context: Context, private val storeList: List<StoreDataResponseItem>): RecyclerView.Adapter<UserViewHolder>() {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {
        val inflator = LayoutInflater.from(viewGroup.context)
        val view = inflator.inflate(R.layout.store_row_item, viewGroup, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        val listItem = storeList[position]
        viewHolder.itemName.text = listItem.title
        viewHolder.itemPrice.text = "Price : "+listItem.price.toString()
        Glide.with(context)
                .load(listItem.image)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.image_not_found)
                .into(viewHolder.image)

        viewHolder.layout.setOnClickListener {
            listener.onClick(it, listItem)
        }
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    interface OnItemClickListener {
        fun onClick(view: View, data: StoreDataResponseItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}

class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var itemName = itemView.tvItemName
    val itemPrice = itemView.tvItemPrice
    val image = itemView.ivItemImage
    val layout = itemView.frameItemView

}