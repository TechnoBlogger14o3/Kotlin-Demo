package com.kotlin.demo.supports

/***
 * Created by Techno Blogger on 22/10/17.
 */
interface AppConstants {
    class Constants {
        val strPressTwice = "Press Twice To Exit"
        val strEnterValidUserName = "Please Enter Valid UserName"
        val strEnterValidEmail = "Please Enter Valid Email"
        val strUserNameSaved = "Email is Saved"
        val strEmailNotProvided = "Email was Not Provided"
        val strTitleHome = "Home"
        val strTitleUserName = "UserName"
        val strImageUrl = "https://koenig-media.raywenderlich.com/uploads/2016/07/Kotlin-feature.png"
        val strNoInternetConnection = "No Internet Connection"
    }

    class BundleKeys {
        val strBundleUserName = "bundleUserName"
    }

    class PrefrenceKeys {
        val strPrefLogin = "prefLogin"
        val strPrefUserName = "prefUserName"
    }
}