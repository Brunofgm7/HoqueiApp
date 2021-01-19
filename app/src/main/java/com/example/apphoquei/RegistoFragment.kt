package com.example.apphoquei

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistoFragment : Fragment() {

    lateinit var botaoLogin: Button
    lateinit var botaoRegisto: Button

    val mAuth = FirebaseAuth.getInstance()
    val Auth = FirebaseFirestore.getInstance().collection("Users")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_registo, container, false)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        botaoRegisto = view.findViewById(R.id.botaoRegisto)
        botaoRegisto.setOnClickListener {
            registo()
        }

        botaoLogin = view.findViewById(R.id.botaoJaTemConta)
        botaoLogin.setOnClickListener {
            val loginFragment = LoginFragment()
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, loginFragment,"findThisFragment")
                    .addToBackStack(null)
                    .commit()
        }
        return view
    }

    private fun registo() {
        val nome = view?.findViewById<EditText>(R.id.textNomeRegisto)?.text.toString()
        val email = view?.findViewById<EditText>(R.id.textEmailRegisto)?.text.toString()
        val password = view?.findViewById<EditText>(R.id.textPasswordRegisto)?.text.toString()

        if (nome.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = HashMap<String, Any>()
                    user["nome"] = nome
                    user["email"] = email
                    val userRef = Auth
                    val uid = mAuth.uid.toString()
                    userRef.document(uid).set(user).addOnCompleteListener {
                        when {
                            it.isSuccessful -> {Toast.makeText(activity,"Registo COM sucesso",Toast.LENGTH_SHORT).show()
                                mAuth.signOut()
                            }
                            else -> {
                                Toast.makeText(activity,"Registo sem sucesso",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    Toast.makeText(activity, "Registo COM sucesso", Toast.LENGTH_SHORT).show()

                    val loginFragment = LoginFragment()
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, loginFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit()

                } else {
                    Toast.makeText(activity, "Registo sem sucesso", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
                Toast.makeText(activity, "Por favor preencha os campos!", Toast.LENGTH_SHORT).show()
        }
    }

        companion object {
        fun newInstance(): RegistoFragment = RegistoFragment()
    }
}