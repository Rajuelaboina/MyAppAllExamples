package com.phycaresolutions.mymap.userjetpack.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomHone() {
    Scaffold {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Home")
        }
    }

}