package com.example.pandasoft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.pandasoft.ui.login.LoginFragment
import kotlinx.android.synthetic.main.login_fragment.view.*


class MainActivity : AppCompatActivity() {

    var handler : Handler? = null
    var run : Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()
        run = Runnable {
            var loginFragment = LoginFragment()
            replaceFragment(loginFragment)

        }
        startHandler()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        stopHandler()
        startHandler()
    }

    fun stopHandler() {
        handler?.removeCallbacks(run)
    }

    fun startHandler() {
        handler?.postDelayed(run, 10 * 60 * 1000)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.my_nav_host_fragment, fragment)
        transaction.commit()
    }
}
