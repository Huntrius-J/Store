package com.example.store

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.store.Models.AppDatabase
import com.example.store.Models.CartData
import com.example.store.databinding.ActivityDetailsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        val image = intent.getStringExtra("image")
        var title = intent.getStringExtra("title")
        var imageV: ImageView = findViewById(R.id.imageView)
        var detailsContent = findViewById<TextView>(R.id.details_content)
        var price = intent.getStringExtra("price")

        var db = AppDatabase.getDatabase(this)

        var addCart = findViewById<FloatingActionButton>(R.id.fab)

        addCart.setOnClickListener{
            var cart = CartData(product_id = intent.getIntExtra("id",0), quanity = 1, image = image, name = title,price = price)
            lifecycleScope.launch(Dispatchers.IO){
                db.cartDao().insertAll(cart)
            }
            Snackbar.make(it, "Добавлено в корзину", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        var priceText = findViewById<TextView>(R.id.priceText)
        priceText.setText(intent.getStringExtra("price"))
        priceText.append("$")

        detailsContent.setText(intent.getStringExtra("description"))

        var ratingBar : RatingBar = findViewById(R.id.ratingBar)
        var ratingCount : TextView = findViewById(R.id.ratingCount)

        ratingBar.setRating(intent.getDoubleExtra("rating", 0.0).toFloat())
        ratingCount.setText(intent.getIntExtra("rated",0).toString())

        Picasso.get().load(image).into(imageV)

        binding.toolbarLayout.title = title
    }
}