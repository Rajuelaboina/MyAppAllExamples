package com.phycaresolutions.mymap.jetpackexamples

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.phycaresolutions.mymap.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen2(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(modifier = Modifier.fillMaxSize(),){
        var name by remember {  mutableStateOf(TextFieldValue("")) }
        var password by remember {  mutableStateOf(TextFieldValue("")) }
        Column(modifier = Modifier.padding(10.dp,100.dp,10.dp,20.dp)) {
            Text(text = "Login", style = TextStyle(
                color = Color.Black,
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline
            )
            )
            OutlinedTextField(value = name, onValueChange ={name = it},
                label = { Text(text = "Name")},
                placeholder = { Text(text = "Enter Name")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                isError = false
               )
            OutlinedTextField(value = password, onValueChange ={password = it},
                label = { Text(text = "Password")},
                placeholder = { Text(text = "Enter password")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                leadingIcon = { Icon(painterResource(id = R.drawable.baseline_attach_file_24), contentDescription ="" )},
                trailingIcon = { Icon(painterResource(id = R.drawable.baseline_volume_up_24), contentDescription = null)}
            )
            Button(onClick = {
                navController.navigate(ScreenRoute.THREE)
             }) {
                 Text(text = "Login")
            }

        }
    }


}