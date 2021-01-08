package com.example.apphoquei

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MudarEmailFragment : Fragment() {

    lateinit var viewEmail: TextView
    lateinit var botaoGuardar: Button
    lateinit var textNovoEmail: EditText
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_mudar_email, container, false)

        viewEmail = view.findViewById(R.id.viewEmail)
        textNovoEmail = view.findViewById(R.id.textNovoEmail)
        botaoGuardar = view.findViewById(R.id.BotaoGuardar)

        botaoGuardar.setOnClickListener {
            editarEmail()
        }

        getdata()

        return view
    }

    companion object {
        fun newInstance(): MudarEmailFragment = MudarEmailFragment()
    }

    private fun editarEmail() {

        val user = mAuth.currentUser

        var novoEmail = textNovoEmail.text.toString()

        if(user != null) {
            if(novoEmail.isNotEmpty()) {
                user.updateEmail(novoEmail).addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        val utilizador = HashMap<String, Any>()
                        utilizador["email"] = novoEmail

                        FirebaseFirestore.getInstance().collection("Users").document(user.uid).update(utilizador)

                        Toast.makeText(activity, "Sucesso! Email atualizado!", Toast.LENGTH_SHORT).show()

                        activity?.finish();
                        this.startActivity(Intent(view?.context, MainActivity::class.java))
                    } else {
                        Toast.makeText(activity, "ERRO: Email não atualizado. Experimente sair da conta e entrar.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun getdata() {
        val user = mAuth.currentUser
        val Auth = FirebaseFirestore.getInstance().collection("Users")
            .get()
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    viewEmail.text = user!!.email
                }
            }
    }




}