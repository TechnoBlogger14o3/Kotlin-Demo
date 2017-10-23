package com.kotlin.demo.fragments

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kotlin.demo.AppSingleton
import com.kotlin.demo.R
import com.kotlin.demo.databinding.FragmentNextBinding
import com.kotlin.demo.supports.AppConstants

/***
 * Created by Techno Blogger on 19/10/17.
 */
class FragmentNextWithBundle : Fragment(), View.OnClickListener {

    var userName: String = ""
    var userEmail: String = ""
    private lateinit var fragmentNext: FragmentNextBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentNext = DataBindingUtil.inflate(inflater, R.layout.fragment_next, container, false)
        val rootView = fragmentNext.root

        AppSingleton.instance.activityInstance!!.setTitle(resources.getString(R.string.username))
        // Getting value by Bundle
        val args = arguments
        userName = args.getString(AppConstants.BundleKeys().strBundleUserName)

        // Getting value by Shared Preferences
        userEmail = AppSingleton.appPreference!!.userEmail

        fragmentNext.txtUserName.text = "UserName is $userName"

        fragmentNext.btnSnackBar.setOnClickListener(this)
        fragmentNext.btnToast.setOnClickListener(this)
        fragmentNext.btnGetEmail.setOnClickListener(this)
        return rootView
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnToast -> Toast.makeText(AppSingleton.instance.activityInstance, userName, Toast.LENGTH_SHORT).show()
            R.id.btnSnackBar -> AppSingleton.instance.activityInstance!!.showSnackBar(view, userName)
            R.id.btnGetEmail -> if (userEmail == "") {
                AppSingleton.instance.activityInstance!!.showSnackBar(view, AppConstants.Constants().strEmailNotProvided)
            } else {
                fragmentNext.txtUserEmail.text = userEmail
            }
        }
    }
}