package com.phycaresolutions.mymap.jetpackexamples

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phycaresolutions.mymap.R
import com.phycaresolutions.mymap.jetpackexamples.ui.theme.MyMapTheme

class UserJetPackActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMapTheme {
                var mDisplayMenu by remember { mutableStateOf(false) }
                var context = LocalContext.current.applicationContext
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "SampleApp") },
                            navigationIcon = {
                                Icon(
                                    painterResource(id = R.drawable.baseline_account_circle_24), contentDescription = "content",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .clickable {
                                            //navController.popBackStack()
                                        })
                            },
                            colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = Color.White,
                            ),
                            actions = {
                                // Creating Icon button favorites, on click
                                // would create a Toast message
                                IconButton(onClick = { Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show() }) {
                                    Icon(Icons.Default.Favorite, "",tint = Color.White)
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(painterResource(id = R.drawable.baseline_logout_24), contentDescription = "logout", tint = Color.White )
                                }

                                // Creating Icon button for dropdown menu
                                IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                                    Icon(Icons.Default.MoreVert, "",tint = Color.White)
                                }
                                DropdownMenu(expanded =mDisplayMenu , onDismissRequest = { mDisplayMenu = false }
                                ) {
                                    // Creating dropdown menu item, on click
                                    // would create a Toast message
                                    DropdownMenuItem(onClick = { Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show() }) {
                                        Text(text = "Settings")
                                    }

                                    // Creating dropdown menu item, on click
                                    // would create a Toast message
                                    DropdownMenuItem(onClick = { Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show() }) {
                                        Text(text = "Logout")
                                    }
                                }
                            }
                        )

                    },


                    ) {
                    val navController = rememberNavController()
                        //AppNavHost(navController,startDestination = ScreenRoute.FIRST)
                   AppNavHost(
                        navController = navController,
                        startDestination = com.phycaresolutions.mymap.userjetpack.ScreenRoute.LOGIN_SCREEN
                    )






                }
            }
        }
    }


}
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = ScreenRoute.FIRST,
)
{
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    )
    {
        composable(com.phycaresolutions.mymap.userjetpack.ScreenRoute.LOGIN_SCREEN){
           val context = LocalContext.current.applicationContext
            var selectedItem by remember{ mutableStateOf( "") }

            val list = listOf("Android","Kotlin","Java","Jetpack Compose","Android","Kotlin","Java","Jetpack Compose","Android","Kotlin","Java","Jetpack Compose","Android","Kotlin","Java","Jetpack Compose","Android","Kotlin","Java","Jetpack Compose","Android","Kotlin","Java","Jetpack Compose","Android","Kotlin","Java","Jetpack Compose","Android","Kotlin","Java","Jetpack Compose")
            Column(modifier = Modifier.padding(10.dp,100.dp,10.dp,20.dp)) {
                LazyColumn {
                    items(list.size){index: Int ->
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedItem == list.get(index),
                                onClick = {
                                    selectedItem = list.get(index)
                                    // Toast.makeText(context, "$selectedItem",Toast.LENGTH_LONG).show()
                                    navController.navigate(ScreenRoute.SECOND)
                                }))

                        {
                            Text(text = list.get(index), color = Color.Red, fontSize = 20.sp)
                        }

                    }
                }
            }

        }
        composable(ScreenRoute.SECOND){
          Screen2(navController)
        }
        composable(ScreenRoute.THREE){
            Screen3(navController)
        }
    }

}


@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyMapTheme {
        Greeting2("Android")
    }
}

