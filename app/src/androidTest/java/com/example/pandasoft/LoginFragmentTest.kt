package com.example.pandasoft

import android.view.View
import com.example.pandasoft.ui.login.LoginFragment
import com.example.pandasoft.ui.login.LoginViewModel
import junit.framework.Assert.assertEquals
import kotlinx.android.synthetic.main.login_fragment.view.*
import org.junit.Before
import org.junit.Test

class LoginFragmentTest {

    private lateinit var loginFragment : LoginFragment

    @Before
    fun setUp(){
        loginFragment = LoginFragment()
    }

    @Test
    fun `showToastWhenNotInputValue`(){
        var output = loginFragment.checkUserPass("" ,"")
//        verify(loginFragment).showAlertToast("please input username and password")
        assertEquals(true, output)
    }
}