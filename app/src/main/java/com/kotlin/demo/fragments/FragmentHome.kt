package com.kotlin.demo.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.demo.AppSingleton
import com.kotlin.demo.R
import com.kotlin.demo.databinding.FragmentHomeBinding
import com.kotlin.demo.supports.AppConstants
import com.kotlin.demo.utility.AppLogger

/***
 * Created by Techno Blogger on 18/10/17.
 */
class FragmentHome : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentHomeBinding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val rootView = fragmentHomeBinding.root

        AppSingleton.instance.activityInstance!!.setTitle(resources.getString(R.string.home))


        // Implementing onClickListener on Button
        fragmentHomeBinding.btnNext.setOnClickListener {
            val userName: String = fragmentHomeBinding.edtUser.text.toString()
            if (userName.length > 3) {
                val bundle = Bundle()
                bundle.putString(AppConstants.BundleKeys().strBundleUserName, userName)
                AppSingleton.instance.flowOrganization.replace(FragmentNextWithBundle(), bundle, false)
            } else {
                AppSingleton.instance.activityInstance!!.showSnackBar(rootView, AppConstants.Constants().strEnterValidUserName)
            }
        }

        fragmentHomeBinding.btnReset.setOnClickListener {
            fragmentHomeBinding.edtUser.setText("")
        }
        return rootView
    }

}