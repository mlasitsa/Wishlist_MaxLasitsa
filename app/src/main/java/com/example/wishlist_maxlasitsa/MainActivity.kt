package com.example.wishlist_maxlasitsa

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val items = mutableListOf<Item>()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ItemAdapter(items)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemNameEditText = findViewById<EditText>(R.id.itemName)
        val itemPriceEditText = findViewById<EditText>(R.id.itemPrice)
        val itemUrlEditText = findViewById<EditText>(R.id.itemUrl)
        val submitButton = findViewById<Button>(R.id.submit)

        submitButton.setOnClickListener {
            val name = itemNameEditText.text.toString()
            val price = itemPriceEditText.text.toString().toDoubleOrNull()
            val url = itemUrlEditText.text.toString()

            if (name.isBlank() || price == null || url.isBlank()) {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = Item(name, price, url)
            adapter.addItem(item)

            itemNameEditText.text.clear()
            itemPriceEditText.text.clear()
            itemUrlEditText.text.clear()
        }
    }
}