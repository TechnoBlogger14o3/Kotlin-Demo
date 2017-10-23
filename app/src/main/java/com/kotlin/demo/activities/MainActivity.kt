package com.kotlin.demo.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.kotlin.demo.AppSingleton
import com.kotlin.demo.R
import com.kotlin.demo.databinding.ActivityMainBinding
import com.kotlin.demo.fragments.FragmentHome
import com.kotlin.demo.fragments.FragmentImage
import com.kotlin.demo.supports.AppConstants
import com.kotlin.demo.utility.FragmentBackHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var backBtnCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        toolbar.title = " "
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            shareIT()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        AppSingleton.instance.init(this)
        AppSingleton.instance.flowOrganization.add(FragmentHome(), false)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                AppSingleton.instance.flowOrganization.clearBackStack()
                AppSingleton.instance.flowOrganization.replace(FragmentHome(), false)
            }
            R.id.nav_image -> {
                AppSingleton.instance.flowOrganization.clearBackStack()
                AppSingleton.instance.flowOrganization.replace(FragmentImage(), false)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        AppSingleton.instance.onDestroy()
    }

    // Implementing "Press Twice to Exit." on a BackPress.
    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            var isToWorkOnBack = true
            if (!AppSingleton.instance.flowOrganization.hasNoMoreBack()) {
                val list = supportFragmentManager.fragments
                if (list != null) {
                    for (i in list.indices.reversed()) {
                        val fragment = list[i]
                        if (fragment is FragmentBackHelper) {
                            isToWorkOnBack = fragment
                                    .onBackPressed()
                            break
                        }
                    }
                }
            }
            if (!isToWorkOnBack)
                return

            if (!AppSingleton.instance.flowOrganization.hasNoMoreBack())
                super.onBackPressed()
            else {
                backBtnCount++
                if (backBtnCount === 2) {
                    System.exit(0)
                    finish()
                    return
                } else {
                    Toast.makeText(AppSingleton.instance.activityInstance, AppConstants().strPressTwice, Toast.LENGTH_SHORT).show()
                    Handler().postDelayed({ backBtnCount = 0 }, 500)
                }
            }
        }
    }

    // Hiding the Keypad.
    private fun hideKeyPad(view: View) {
        val imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Showing the snackBar, making it public so that all Fragment can access this.
    fun showSnackBar(view: View?, message: String) {
        if (view != null)
            try {
                hideKeyPad(view)
                val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                val snackbarView = snackbar.view
                snackbarView.setBackgroundColor(Color.DKGRAY)
                val textView = snackbarView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.YELLOW)
                snackbar.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
    }

    // Simple function of sharing the app link.
    private fun shareIT() {
        try {
            val appPackageName = packageName
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "Kotlin Demo")
            var sAux = "\nLet me recommend you this application\n\n"
            sAux += ("https://play.google.com/store/apps/details?id=" + appPackageName)
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            startActivity(Intent.createChooser(i, "Select One"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Setting the toolbar title.
    fun setTitle(title: String) {
        txtTitle.text = title
    }

}
