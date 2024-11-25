package com.phycaresolutions.mymap.userjetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phycaresolutions.mymap.userjetpack.ui.theme.MyMapTheme

class LoginToActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            MyMapTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                   // AppNavHost(navController,startDestination = ScreenRoute.LOGIN_SCREEN)
                    AppNavHost(navController = navController, startDestination = ScreenRoute.LOGIN_SCREEN)

                }

            }
        }
    }


}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = ScreenRoute.LOGIN_SCREEN,
)
{
     NavHost(
         modifier = modifier,
         navController = navController,
         startDestination = startDestination
     )
     {
         composable(ScreenRoute.LOGIN_SCREEN){
          loginScreen(navController)
         }
         composable(ScreenRoute.HOME_SCREEN){
              HomeScreen(navController)
         }
     }

}


@Composable
fun loginScreen(navController: NavHostController) {
    val context = LocalContext.current.applicationContext
    //val navController = rememberNavController()
    //val navStackBackEntry by navController.currentBackStackEntryAsState()
    // var currentRoute = navStackBackEntry?.destination?.route?:ScreenRoute.LOGIN_SCREEN
    var username by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var passwordvisible by rememberSaveable { mutableStateOf(false) }
    var isValidUser by remember { mutableStateOf(false) }
    var isValidPwd by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        OutlinedTextField(
            value = username,
            onValueChange = {
                isValidUser = false
                username = it
                            },
            placeholder = { Text(text = "Username") },
            label = { Text(text = "Username") },
            singleLine = true,
            isError = isValidUser,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

        )
         Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(value = password ,
            onValueChange = {
                isValidPwd = false
                password = it},
            placeholder = { Text(text = "password")},
            label = { Text(text = "Password")},
            singleLine = true,
            isError = isValidPwd,
            visualTransformation = if (passwordvisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Button(
            onClick = {
                /*if (isValidUserName(username)){
                    isValidUser = true
                   // Toast.makeText(context,"field is not empty!",Toast.LENGTH_LONG).show()
                }else if (isValidPassword(password)){
                    isValidPwd = true
                    //Toast.makeText(context,"field is not empty!",Toast.LENGTH_LONG).show()

                }
                else{
                    navController.currentBackStackEntry?.savedStateHandle?.set("UNAME",username)
                    navController.currentBackStackEntry?.savedStateHandle?.set("PWD",password)
                    navController.navigate(ScreenRoute.HOME_SCREEN)
                }*/
                navController.currentBackStackEntry?.savedStateHandle?.set("UNAME",username)
                navController.currentBackStackEntry?.savedStateHandle?.set("PWD",password)
                navController.navigate(ScreenRoute.HOME_SCREEN)
            }
        ){ 
           Text(text = "Login")
        }
    }

}

fun isValidUserName(username: String): Boolean {
    var boolean = if (username.isEmpty()){
          true
      }else {
          false
      }

    return boolean
}
fun isValidPassword(password: String): Boolean {
    var boolean = if (password.isEmpty()){
        true
    }else {
        false
    }

    return boolean
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyMapTheme {
        //Greeting("Android")
        val navController = rememberNavController()
        AppNavHost(navController = navController, startDestination = ScreenRoute.LOGIN_SCREEN)

    }
}