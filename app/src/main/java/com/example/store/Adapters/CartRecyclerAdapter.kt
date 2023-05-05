package com.example.store.Adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Interface.CartCellClickListener
import com.example.store.Models.Cart
import com.example.store.R

class CartRecyclerAdapter (private val context: Context, private val cartList: MutableList<Cart>, private val ncellClickListener: CartCellClickListener): RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val view: View) :RecyclerView.ViewHolder(view)
    {
        val tvDate: TextView = view.findViewById(R.id.tvProdTitle)
        val tvCartContent: TextView = view.findViewById(R.id.tvCartContent)
        val imageView: ImageView = view.findViewById(R.id.cartTempImage)
    }
    val inflater = LayoutInflater.from(context)



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartRecyclerAdapter.ViewHolder {
        val view = inflater.inflate(R.layout.cart_item, parent,false)
        return CartRecyclerAdapter.ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CartRecyclerAdapter.ViewHolder, position: Int) {

        holder.tvDate.text = cartList[position].date.toString()
        holder.tvCartContent.text = cartList[position].products.size.toString() + " продукта"
        holder.imageView.setImageResource(R.drawable.shoppingbasket_114880)


        val prop = cartList[position]
        holder.itemView.setOnClickListener {
            ncellClickListener.cellClickListener(prop)
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}