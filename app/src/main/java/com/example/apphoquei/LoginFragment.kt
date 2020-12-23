package com.example.apphoquei

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    lateinit var textEmail: EditText
    lateinit var textPassword: EditText
    lateinit var botaoRegisto: Button
    lateinit var botaoLogin: Button
    val mAuth = FirebaseAuth.getInstance()


    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        textEmail = view.findViewById(R.id.textEmail)
        textPassword = view.findViewById(R.id.textPassword)
        botaoRegisto = view.findViewById(R.id.botaoJaTemConta)
        botaoLogin = view.findViewById(R.id.botaoLogin)

        botaoLogin.setOnClickListener {
            var email: String = textEmail.text.toString()
            var pass: String = textPassword.text.toString()

            login(email, pass)
        }

        botaoRegisto.setOnClickListener {
            val RegistoFragment = RegistoFragment()
            activity!!.supportFragmentManager.beginTransaction()
                .replace(com.example.apphoquei.R.id.frame_layout, RegistoFragment, "findThisFragment")
                .addToBackStack(null)
                .commit()
        }
        return view
    }

    private fun login(email: String, pass: String) {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Login COM sucesso ", Toast.LENGTH_SHORT).show()

//                    val loggedinFragment = ResultadosFragment()
//                    activity!!.supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, loggedinFragment, "findThisFragment")
//                        .addToBackStack(null)
//                        .commit()
                } else {
                    Toast.makeText(activity, "Login sem sucesso ", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(activity, "Por favor preencha os campos!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(): MinhasEquipasFragment = MinhasEquipasFragment()
    }
}