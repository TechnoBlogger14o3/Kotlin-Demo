package com.kotlin.demo.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kotlin.demo.AppSingleton
import com.kotlin.demo.R
import com.kotlin.demo.databinding.FragmentImageBinding
import com.kotlin.demo.supports.AppConstants
import com.kotlin.demo.utility.CheckNetworkState

/***
 * Created by Techno Blogger on 22/10/17.
 */
class FragmentImage : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentImageBinding: FragmentImageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false)
        val rootView = fragmentImageBinding.root

        AppSingleton.instance.activityInstance!!.setTitle(resources.getString(R.string.image))
        fragmentImageBinding.btnDownload.setOnClickListener {
            if (CheckNetworkState(context).isNetworkAvailable) {
                Glide.with(context)
                        .load(AppConstants().strImageUrl)
                        .into(fragmentImageBinding.imgPreview)
            } else {
                AppSingleton.instance.activityInstance!!.showSnackBar(view, AppConstants().strNoInternetConnection)
            }
        }
        return rootView
    }

}