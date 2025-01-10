package com.phycaresolutions.mymap.multiviewrecycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.phycaresolutions.mymap.R

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val list = mutableListOf<Any>(
            TextItem("Kotlin"),
            TextItem("Kotlin"),
            TextItem("Kotlin"),
            TextItem("Kotlin"),
            TextItem("Kotlin"),
            TextItem("Kotlin"),

                    ImageItem(R.drawable.cat),
                    TextItem("Android"),
            ImageItem(R.drawable.dog),
            ImageItem(R.drawable.cat),
            ImageItem(R.drawable.cat),
            ImageItem(R.drawable.cat),
            ImageItem(R.drawable.cat),
            AdItem("Android"),
            ImageItem(R.drawable.cat),
            TextItem("Android"),
            ImageItem(R.drawable.dog),
            ImageItem(R.drawable.cat),
            ImageItem(R.drawable.cat),
            ImageItem(R.drawable.cat),
            ImageItem(R.drawable.cat),
            AdItem("Android"),
            ImageItem(R.drawable.cat),
            TextItem("Android"),
            ImageItem(R.drawable.dog),
            AdItem("Android"),

            ImageItem(R.drawable.cat),
            ImageItem(R.drawable.cat),
            )
        val adapter = DataAdapter(list)
        recyclerView.adapter = adapter
    }
    class DataAdapter(val list: MutableList<Any>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
           //return MyViewHoder(LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false))
          return  when(viewType){
                   0 -> ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.text_item,parent,false))
                   1 -> ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item,parent,false))
                   2 -> AdItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ad_item,parent,false))
              else -> throw IllegalArgumentException("Invalid view type")
          }
        }

        override fun getItemCount(): Int {
           return list.size
        }

        override fun getItemViewType(position: Int): Int {
            return when(list[position]){
                is TextItem -> 0
                is ImageItem -> 1
                is AdItem -> 2
                else -> throw IllegalArgumentException("Invalid type of data" + position)
            }

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //((holder) as MyViewHoder).tv.text = list.get(position)
            when(holder){
                is ItemViewHolder ->{
                    val textItem = list[position] as TextItem
                    holder.tv.text =textItem.name
                }
                is ImageViewHolder -> {
                    val imageItem = list[position] as ImageItem
                    holder.img.setImageResource(imageItem.imgId)

                }
                is AdItemViewHolder -> {
                    val addItem = list[position] as AdItem
                    holder.tv.text = addItem.string
                }
            }
        }
        class ItemViewHolder(itemView: View) :ViewHolder(itemView){
            var tv =itemView.findViewById<TextView>(R.id.textviewone)

        }
        class ImageViewHolder(itemView: View) :ViewHolder(itemView){
            var img =itemView.findViewById<ImageView>(R.id.imageone)

        }
        class AdItemViewHolder(itemView: View) :ViewHolder(itemView){
            var tv =itemView.findViewById<TextView>(R.id.textviewtwo)

        }

       /* override fun getItemViewType(position: Int): Int {
            if (position == -1){
                return 1
            }else{
                return 2
            }
           // return super.getItemViewType(position)
        }*/

    }
}