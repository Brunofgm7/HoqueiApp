package com.example.apphoquei

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class LoginFragment : Fragment() {
    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val botaoRegisto = view.findViewById(R.id.botaoJaTemConta) as Button

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
        fun newInstance(): MinhasEquipasFragment = MinhasEquipasFragment()
    }
}