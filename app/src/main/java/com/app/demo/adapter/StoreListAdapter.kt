package com.app.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.demo.R
import com.app.demo.data.StoreDataResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.store_row_item.view.*


class StoreListAdapter(val context: Context, private val storeList: List<StoreDataResponseItem>): RecyclerView.Adapter<UserViewHolder>() {

    lateinit var listener: OnItemClickListener
    var mCounter = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {
        val inflator = LayoutInflater.from(viewGroup.context)
        val view = inflator.inflate(R.layout.store_row_item, viewGroup, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        val listItem = storeList[position]
        viewHolder.itemName.text = listItem.title
        viewHolder.itemPrice.text = listItem.price.toString()
        Glide.with(context)
                .load(listItem.image)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.image_not_found)
                .into(viewHolder.image)

        viewHolder.image.setOnClickListener {
            listener.onClick(it, listItem)
        }

        viewHolder.addItem.setOnClickListener {
            mCounter++
            viewHolder.itemCount.text = (mCounter).toString()
        }

        viewHolder.removeItem.setOnClickListener {
            if(mCounter > 0) {
                mCounter--
            } else {
                mCounter = 0
            }
            viewHolder.itemCount.text = (mCounter).toString()

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
    val image = itemView.ivItem
    val addItem = itemView.ivAddItem
    val removeItem = itemView.ivRemoveItem
    val itemCount = itemView.itemCount

}