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
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentNext: FragmentNextBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_next, container, false)
        val rootView = fragmentNext.root

        AppSingleton.instance.activityInstance!!.setTitle(resources.getString(R.string.username))
        val args = arguments
        userName = args.getString(AppConstants.BundleKeys().strBundleUserName)

        if (userName != null) {
            fragmentNext.txtUserName.text = "UserName is $userName"
        }

        fragmentNext.btnSnackBar.setOnClickListener(this)
        fragmentNext.btnToast.setOnClickListener(this)
        return rootView
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnToast -> Toast.makeText(AppSingleton.instance.activityInstance, userName, Toast.LENGTH_SHORT).show()
            R.id.btnSnackBar -> AppSingleton.instance.activityInstance!!.showSnackBar(view, userName)
        }
    }

}