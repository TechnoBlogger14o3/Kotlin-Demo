package com.kotlin.demo.utility

import android.support.v4.app.Fragment

import com.kotlin.demo.interfaces.IOnBackPressed


/***
 * Created by Techno Blogger on 20/5/16.
 */
class FragmentBackHelper : Fragment(), IOnBackPressed {
    override fun onBackPressed(): Boolean {
        return true
    }
}
