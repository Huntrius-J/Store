package com.example.store.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Interface.ProdCellClickListener
import com.example.store.Models.Product
import com.example.store.R
import com.squareup.picasso.Picasso

class ProdsRecyclerAdapter(private val context: Context, private val prodList: MutableList<Product>,private val ncellClickListenerProd: ProdCellClickListener): RecyclerView.Adapter<ProdsRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val view:View) :RecyclerView.ViewHolder(view)
    {
        val tvTitle: TextView = view.findViewById(R.id.tvProdTitle)
        //val tvContent: TextView = view.findViewById(R.id.tvCartContent)
        val tvPrice: TextView = view.findViewById(R.id.tvProdPrice)
        val imageView: ImageView = view.findViewById(R.id.cartTempImage)
    }
    val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.prod_item, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return prodList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = prodList[position].title.toString()
        //holder.tvContent.text = prodList[position].description.toString()
        try {
            holder.tvPrice.text = prodList[position].price.toString() + "$"
            Picasso.get().load(prodList[position].image).into(holder.imageView)
        }
        catch (ex:java.lang.Exception) {

        }

        val prop = prodList[position]
        holder.itemView.setOnClickListener {
            ncellClickListenerProd.cellClickListener(prop)
        }
    }

}