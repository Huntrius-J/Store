package com.example.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var sumText : TextView = findViewById(R.id.sumText)
        sumText.append(intent.getStringExtra("sum"))
    }
}