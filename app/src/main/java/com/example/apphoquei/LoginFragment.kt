package com.example.apphoquei

import android.app.AlertDialog
import android.content.DialogInterface
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

class LoginFragment : Fragment() {

    lateinit var textEmail: EditText
    lateinit var textPassword: EditText
    lateinit var botaoRegisto: Button
    lateinit var botaoLogin: Button
    lateinit var botaoForgotYourPassword : TextView
    val mAuth = FirebaseAuth.getInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        textEmail = view.findViewById(R.id.textEmail)
        textPassword = view.findViewById(R.id.textPassword)
        botaoRegisto = view.findViewById(R.id.botaoJaTemConta)
        botaoLogin = view.findViewById(R.id.botaoLogin)
        botaoForgotYourPassword = view.findViewById(R.id.botaoForgotYourPassword)

        botaoLogin.setOnClickListener {
            var email: String = textEmail.text.toString()
            var pass: String = textPassword.text.toString()

            login(email, pass)
        }

        botaoRegisto.setOnClickListener {
            val registoFragment = RegistoFragment()
            activity!!.supportFragmentManager.beginTransaction()
                .replace(com.example.apphoquei.R.id.frame_layout, registoFragment, "findThisFragment")
                .addToBackStack(null)
                .commit()
        }

        botaoForgotYourPassword.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Forgot your password")
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val email = view.findViewById<EditText>(R.id.Email_recuperarPassword)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotPassword(email)
            })
            builder.setNegativeButton("Fechar", DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }


        return view
    }


    private fun forgotPassword(email : EditText) {
        if(email.text.toString().isEmpty()) {
            return
        }

        mAuth.sendPasswordResetEmail(email.text.toString())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(activity, "Se o email existir na base de dados, irá receber um email.", Toast.LENGTH_SHORT).show()
                    }
                }

    }

    private fun login(email: String, pass: String) {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    activity?.finish();
                    this.startActivity(Intent(view?.context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
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