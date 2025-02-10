package com.phycaresolutions.mymap.jetpackexamples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun Screen3(navController: NavHostController) {
  Column {
      Row {
          Text(text = "this Second screen")
      }
  }
}