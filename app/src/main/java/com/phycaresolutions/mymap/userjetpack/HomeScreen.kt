package com.phycaresolutions.mymap.userjetpack

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.phycaresolutions.mymap.R
import com.phycaresolutions.mymap.userjetpack.screens.BottomHone
import com.phycaresolutions.mymap.userjetpack.screens.BottomProfile
import com.phycaresolutions.mymap.userjetpack.screens.BottomSettings
import com.phycaresolutions.mymap.userjetpack.screens.Screen2
import com.phycaresolutions.mymap.userjetpack.screens.Screen3
import com.phycaresolutions.mymap.userjetpack.screens.Screen4
import com.phycaresolutions.mymap.userjetpack.screens.Screen5
import com.phycaresolutions.mymap.userjetpack.screens.Screen6
import com.phycaresolutions.mymap.userjetpack.screens.ScreenOne


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    val navController2 = rememberNavController()
    val navBackStackEntry by navController2.currentBackStackEntryAsState()
    var currentRoute = navBackStackEntry?.destination?.route?:BottomRoute.HOME
    val str = navController.previousBackStackEntry?.savedStateHandle?.get<String>("UNAME")
    val str1 = navController.previousBackStackEntry?.savedStateHandle?.get<String>("PWD")

    val coroutineScope = rememberCoroutineScope()


       Column(modifier = Modifier.fillMaxSize(),
           verticalArrangement = Arrangement.Top)
        {

           var tabIndex by remember { mutableStateOf(0) }
              val tabData = listOf(
                   "Android" to Icons.Default.Home,
                   "Kotlin" to Icons.Default.Person,
                   "Java" to Icons.Default.Notifications,
                   "Angular" to Icons.Default.Home,
                   "Reactive" to Icons.Default.Person,
                   "JavaScript" to Icons.Default.Notifications
               )



           // bottom navigation
           Scaffold(
               topBar = {
                   TopAppBar(
                       title = { Text(currentRoute, fontSize = 18.sp) },
                       navigationIcon = {
                           Icon(imageVector = Icons.Default.Menu,
                               contentDescription =
                               stringResource(id = R.string.app_name),
                               modifier = Modifier
                                   .padding(16.dp)
                                   .clickable {
                                       //navController.popBackStack()
                                   }
                           )
                       },
                   )
               },
               bottomBar = {BottomNavigationBar(navController2)}
           )
           {
               AppBottomNavigation(navController2)
               Column(modifier = Modifier.padding(top = 50.dp)) {
                   ScrollableTabRow(selectedTabIndex = tabIndex,
                       edgePadding = 16.dp,
                       containerColor = colorResource(id = R.color.colorPrimaryDark),
                       indicator = { tabPositions ->
                           SecondaryIndicator(
                               modifier = Modifier
                                   .tabIndicatorOffset(tabPositions[tabIndex])
                                   .fillMaxWidth(),
                               height = 3.dp,
                               color = Color.Black
                           )
                       }
                   ) {
                       tabData.forEachIndexed { index, pair ->
                           Tab(selected = tabIndex == index,
                               onClick = {
                                   tabIndex = index
                                   //selectedTabIndex = index
                                   //  coroutineScope.launch {
                                   // pagerState.animateScrollToPage(index)
                                   // }
                               },
                               modifier = Modifier.padding(20.dp),
                               selectedContentColor = Color.Black,
                               unselectedContentColor = Color.White,
                               content = {
                                   Row(
                                       verticalAlignment = Alignment.CenterVertically,
                                       horizontalArrangement = Arrangement.Start
                                   )
                                   {
                                       Icon(
                                           imageVector = pair.second,
                                           contentDescription = pair.first,
                                           modifier = Modifier.size(20.dp),
                                           tint = Color.Unspecified
                                       )
                                       Text(text = pair.first,)
                                   }
                               }
                           )
                       }
                   }

                   // Tab content

                   when (tabIndex) {
                       0 -> ScreenOne()
                       1 -> Screen2()
                       2 -> Screen3()
                       3 -> Screen4()
                       4 -> Screen5()
                       5 -> Screen6()
                   }

               }
           }


       }


   }



@Composable
fun AppBottomNavigation(navController2: NavHostController) {
    NavHost(navController = navController2, startDestination = BottomRoute.HOME) {
         composable(BottomRoute.HOME) { BottomHone() }
        composable(BottomRoute.PROFILE) {  BottomProfile()}
        composable(BottomRoute.SETTINGS) { BottomSettings() }
    }
}

@Composable
fun BottomNavigationBar(navController2: NavHostController) {
    val navBackStackEntry by navController2.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val seletedItemIndex by rememberSaveable { mutableStateOf(0) }
    NavigationBar(containerColor = Color.White) {
        bottomNavigationItems.forEachIndexed { index, navigationItem ->

         NavigationBarItem(
             selected = currentRoute == navigationItem.route,
             onClick = {
                 navController2.navigate(navigationItem.route) {
                     popUpTo(navController2.graph.startDestinationId)
                     launchSingleTop = true
                 }
             },
             icon = {
                 Icon(imageVector = navigationItem.icon, contentDescription = navigationItem.label)
             },
             label = { Text(text = navigationItem.label) },
             alwaysShowLabel = true,
             colors = NavigationBarItemDefaults.colors(
                 selectedTextColor = colorResource(id = R.color.colorPrimary),
                 selectedIconColor = Color.White,
                 unselectedIconColor = Color.Black,
                 unselectedTextColor = Color.Black,
                 indicatorColor = colorResource(id = R.color.colorPrimaryDark)
             ),

             )
        }

    }
}
val bottomNavigationItems = listOf(
    NavigationItem(BottomRoute.HOME,"Home", Icons.Filled.Home,Icons.Outlined.Home,false,null),
    NavigationItem(BottomRoute.PROFILE,"Profile", Icons.Filled.AccountCircle,Icons.Outlined.AccountCircle,false,null),
    NavigationItem(BottomRoute.SETTINGS,"Settings", Icons.Filled.Settings,Icons.Outlined.Settings,false,null)
)
/*
 var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val activeChanged: (Boolean) -> Unit = { active ->
        searchQuery = ""
    }
    val items = listOf("java","android","ios","js","abcd")
    var filteredItems = items.filter { it.contains(searchQuery, ignoreCase = true) }
    var isSearchActive by rememberSaveable { mutableStateOf(false) }
Row {
             SearchBar(
                 query = searchQuery ,
                 onQueryChange = {searchQuery = it} ,
                 onSearch = {active = false} ,
                 active = active,
                 onActiveChange = {active = it},
                 modifier = Modifier
                     .padding(start = 12.dp, top = 2.dp, end = 12.dp, bottom = 12.dp)
                     ,
                 placeholder = { Text(text = "Search")},
                 leadingIcon = {
                     if (active) {
                     IconButton(
                         onClick = {active = false },
                     ) {
                         Icon(
                             imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                             contentDescription = stringResource(R.string.title_activity_home),
                             tint = MaterialTheme.colorScheme.primary,
                         )
                     }
                 } else {
                     Icon(
                         imageVector = Icons.Rounded.Search,
                         contentDescription = null,
                         tint = MaterialTheme.colorScheme.onSurfaceVariant,
                     )
                 }
                               } ,
                 trailingIcon = {
                     if (active)
                         Icon(imageVector = Icons.Rounded.Close, contentDescription = "" )
                 },
                 colors = SearchBarDefaults.colors(
                     containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                 ),
                 tonalElevation = 0.dp
             ) {
                 //Search content here

                 filteredItems.forEach{ item->
                     Text(text = item, fontSize = 15.sp)
                 }
             }
             Image( painter = painterResource(id = R.drawable.baseline_account_circle_24),
                 contentDescription = "",
                 modifier = Modifier.padding().align(Alignment.CenterVertically))
         }*/