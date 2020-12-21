package com.example.apphoquei

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class LoginFragment : Fragment() {

    lateinit var BotaoRegisto : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(com.example.apphoquei.R.layout.fragment_perfil, container, false)

        val botaoRegisto: Button = view.findViewById(R.id.button2)
        botaoRegisto.setOnClickListener {
            val RegistoFragment = RegistoFragment()
            activity!!.supportFragmentManager.beginTransaction()
                .replace(com.example.apphoquei.R.id.frame_layout, RegistoFragment, "findThisFragment")
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }

//    override fun onClick(v: View?) {
//        when(v?.id) {
//            R.id.button2 -> {
//
//            }
//        }
//    }


}