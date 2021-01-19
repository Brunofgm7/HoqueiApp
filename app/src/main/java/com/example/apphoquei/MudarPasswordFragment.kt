package com.example.apphoquei

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MudarPasswordFragment : Fragment() {

    lateinit var textNovaPass: EditText
    lateinit var textRepetirNovaPass: EditText
    lateinit var botaoGuardar: Button
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_mudar_password, container, false)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        textNovaPass = view.findViewById(R.id.textNovaPass)
        textRepetirNovaPass = view.findViewById(R.id.textRepetirNovaPass)

        botaoGuardar = view.findViewById(R.id.botaoGuardar)
        botaoGuardar.setOnClickListener {
            mudarPass()
        }

        return view
    }

    companion object {
        fun newInstance(): MudarPasswordFragment = MudarPasswordFragment()
    }

    private fun mudarPass() {

        val user = mAuth.currentUser
        var novaPass = textNovaPass.text.toString()
        var repetirNovaPass = textRepetirNovaPass.text.toString()

        if (novaPass.isNotEmpty() && repetirNovaPass.isNotEmpty()) {
            if (novaPass == repetirNovaPass) {
                user!!.updatePassword(novaPass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity, "SUCESSO! Palavra-passe atualizada!", Toast.LENGTH_LONG).show()
                        activity?.finish();
                        this.startActivity(Intent(view?.context, MainActivity::class.java))
                    } else {
                        Toast.makeText(activity, "ERRO: Palavra-passe não atualizada. Experimente sair da conta e entrar.", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(activity, "Palavras-passes não correspondentes", Toast.LENGTH_SHORT).show()
            }
        }
    }
}