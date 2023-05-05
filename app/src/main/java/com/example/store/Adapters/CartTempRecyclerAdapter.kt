package com.example.store.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Models.Cart
import com.example.store.Models.CartData
import com.example.store.R
import com.squareup.picasso.Picasso

class CartTempRecyclerAdapter (private val context: Context, private val cartList: List<CartData>): RecyclerView.Adapter<CartTempRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvProdTitle)
        val tvPrice: TextView = view.findViewById(R.id.tvProdPrice)
        val quainty: EditText = view.findViewById(R.id.tvQuanity)
        val imageView: ImageView = view.findViewById(R.id.cartTempImage)
    }

    val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.prodcart_item, parent,false)
        return CartTempRecyclerAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = cartList[position].name.toString()
        holder.quainty.setText(cartList[position].quanity.toString())
        holder.tvPrice.text = cartList[position].price.toString() + "$"
        try {
            Picasso.get().load(cartList[position].image).into(holder.imageView)
        }
        catch (ex:java.lang.Exception) {

        }

        val prop = cartList[position]
    }
}