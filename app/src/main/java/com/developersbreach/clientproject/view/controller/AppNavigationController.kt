package com.developersbreach.clientproject.view.controller

import androidx.navigation.NavController
import com.developersbreach.clientproject.model.Customers


fun dashboardToSettings(
    navController: NavController
) {
//    navController.navigate(
//        DashboardFragmentDirections.dashboardToSettingsFragment()
//    )
}

fun dashboardToBillNumber(
    navController: NavController
) {
//    navController.navigate(
//        DashboardFragmentDirections.dashboardToBillNumberFragment()
//    )
}

fun dashboardToContact(
    navController: NavController
) {
//    navController.navigate(
//        DashboardFragmentDirections.dashboardToContactFragment()
//    )
}

fun dashboardToServices(
    navController: NavController
) {
//    navController.navigate(
//        DashboardFragmentDirections.dashboardToServicesFragment()
//    )
}

fun dashboardToIntro(
    navController: NavController
) {
//    navController.navigate(
//        DashboardFragmentDirections.dashboardToIntroFragment()
//    )
}

fun dashboardToLogin(
    navController: NavController
) {
//    navController.navigate(
//        DashboardFragmentDirections.dashboardToLoginFragment()
//    )
}

fun dashboardToDetail(
    customer: Customers,
    photoUrl: String,
    navController: NavController
) {
//    navController.navigate(
//        DashboardFragmentDirections.dashboardToDetailFragment(
//            customer, photoUrl
//        )
//    )
}

fun billNumberToDashboard(
    navController: NavController,
    customer: Customers?
) {
//    navController.navigate(
//        BillNumberFragmentDirections.billNumberToDashboardFragment(customer)
//    )
}

fun introToDashboard(
    navController: NavController
) {
//    navController.navigate(
//        IntroFragmentDirections.introToDashboardFragment()
//    )
}

fun settingsToLogin(
    navController: NavController
) {
//    navController.navigate(
//        SettingsFragmentDirections.settingsToLoginFragment()
//    )
}

fun settingsToIntro(
    navController: NavController
) {
//    navController.navigate(
//        SettingsFragmentDirections.settingsToIntroFragment()
//    )
}