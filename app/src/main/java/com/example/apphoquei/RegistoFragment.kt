package com.example.apphoquei

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class RegistoFragment : Fragment() {
    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_registo, container, false)

        val botaoLogin = view.findViewById(R.id.botaoJaTemConta) as Button

        botaoLogin.setOnClickListener {
            val LoginFragment = LoginFragment()
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(com.example.apphoquei.R.id.frame_layout, LoginFragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit()
        }
        return view
    }

        companion object {
        fun newInstance(): RegistoFragment = RegistoFragment()
    }
}