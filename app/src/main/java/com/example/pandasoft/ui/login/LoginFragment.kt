package com.example.pandasoft.ui.login

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.Navigation
import com.example.pandasoft.R
import kotlinx.android.synthetic.main.login_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment: Fragment() {

    private lateinit var rootView: View
    private val viewModel: LoginViewModel by viewModel()

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
            val bundle = Bundle()

            val navController = Navigation.findNavController(activity!!, R.id.my_nav_host_fragment)
            btn_login.setOnClickListener {
                viewModel.doLogin(edt_username.text.toString() , edt_password.text.toString())
//                navController.navigate(R.id.action_loginFragment_to_newsListFragment, bundle)
            }
        }
    }
}