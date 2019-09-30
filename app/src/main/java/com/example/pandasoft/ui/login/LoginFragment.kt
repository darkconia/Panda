package com.example.pandasoft.ui.login

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.pandasoft.R
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment: Fragment() {

    private lateinit var rootView: View
    private val viewModel: LoginViewModel by viewModel()
    private lateinit var navController : NavController
    private lateinit var bundle : Bundle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.login_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindView()
    }


    private fun onBindView() {
        rootView.apply {
            bundle = Bundle()
            navController = Navigation.findNavController(activity!!, R.id.my_nav_host_fragment)

            btn_login.setOnClickListener {
                checkUserPass(edt_username.text.toString() ,edt_password.text.toString())
            }
        }
    }

    fun checkUserPass(user : String , pass : String){
        if(user == "" || pass == ""){
            showAlertToast("Please input username and password")
        }else{
            viewModel.doLogin(edt_username.text.toString() , edt_password.text.toString())
            navController.navigate(R.id.action_loginFragment_to_newsListFragment, bundle)
        }
    }

    fun showAlertToast(msg : String){
        Toast.makeText(activity, msg , Toast.LENGTH_SHORT).show()
    }
}