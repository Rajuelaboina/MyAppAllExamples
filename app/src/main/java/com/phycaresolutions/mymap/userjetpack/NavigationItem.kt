package com.phycaresolutions.mymap.userjetpack

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(val route: String,
                          val label: String,
                          val icon: ImageVector,
                          val unselectedIcon: ImageVector,
                          val hasNews: Boolean,
                          val badgeCount: Int? = null)
