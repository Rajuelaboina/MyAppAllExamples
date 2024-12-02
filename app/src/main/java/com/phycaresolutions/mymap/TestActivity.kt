package com.phycaresolutions.mymap

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test)

        val list = listOf("Android","Android","Java","Kotlin")
        Log.e("List Data","Data: " + list)
        list.distinct()
        list.toSet()
        list.toHashSet()
        list.toMutableSet()
        //  A class should have only one reason to change
        // mvvm
        // model :responsible for abstraction of the data source
        // view : inform the view model about the user action
        // viewModel : those data streams which are relevant to the view


    }
}