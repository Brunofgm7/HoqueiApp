package com.example.apphoquei

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PerfilFragment : Fragment() {

    lateinit var botaoLogout: Button
    lateinit var botaoMudarPass: Button
    lateinit var botaoMudarEmail: Button
    lateinit var textViewEmail: TextView
    lateinit var textViewNome: TextView
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        botaoLogout = view.findViewById(R.id.botaoLogout)
        botaoMudarEmail = view.findViewById(R.id.botaoMudarEmail)
        botaoMudarPass = view.findViewById(R.id.botaoMudarPassword)
        textViewEmail = view.findViewById(R.id.textViewEmail)
        textViewNome = view.findViewById(R.id.textViewNome)


        botaoLogout.setOnClickListener {
            mAuth.signOut()
            activity?.finish();
            this.startActivity(
                    Intent(
                            view.context,
                            MainActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }

        botaoMudarEmail.setOnClickListener {
            val mudarEmailFragment = MudarEmailFragment()
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(
                            R.id.frame_layout,
                            mudarEmailFragment,
                            "findThisFragment"
                    )
                    .addToBackStack(null)
                    .commit()
        }

        botaoMudarPass.setOnClickListener {
            val mudarPasswordFragment = MudarPasswordFragment()
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(
                            R.id.frame_layout,
                            mudarPasswordFragment,
                            "findThisFragment"
                    )
                    .addToBackStack(null)
                    .commit()
        }


        getdata()

        return view
    }


    companion object {
        fun newInstance(): PerfilFragment = PerfilFragment()
    }

    private fun getdata() {
        val user = mAuth.currentUser

        val auth = FirebaseFirestore.getInstance().collection("Users")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    textViewEmail.text = user!!.email
                }

                if (user != null) {
                    FirebaseFirestore.getInstance().collection("Users").document(user.uid)
                        .get()
                        .addOnSuccessListener { document ->
                            if(document != null) {
                                val nome = document.data?.get("nome").toString()
                                textViewNome.text = nome
                            }
                        }
                        .addOnFailureListener {
                            textViewNome.text = ""
                        }
                }
            }
    }
}