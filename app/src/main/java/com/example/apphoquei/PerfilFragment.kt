package com.example.apphoquei

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


class PerfilFragment : Fragment() {

    lateinit var botaoLogout: Button
    val mAuth = FirebaseAuth.getInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        botaoLogout = view.findViewById(R.id.botaoLogout)

        botaoLogout.setOnClickListener {
            mAuth.signOut()
            val loggedOutFragment = LoginFragment()
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, loggedOutFragment, "findThisFragment")
                .addToBackStack(null)
                .commit()
            this.startActivity(Intent(view.context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))

//            (activity as MainActivity?)?.recreate()
//            (activity as MainActivity).overridePendingTransition(0, 0)
        }
        return view
    }


    companion object {
        fun newInstance(): PerfilFragment = PerfilFragment()
    }
}