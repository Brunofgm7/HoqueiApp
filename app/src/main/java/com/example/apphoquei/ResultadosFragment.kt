package com.example.apphoquei

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_resultados.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ResultadosFragment : Fragment() {

    lateinit var mostrarData: TextView
    private val dbFire : FirebaseFirestore = FirebaseFirestore.getInstance()

    var arrayJogos = ArrayList<JogoItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {

        val view = inflater.inflate(R.layout.fragment_resultados, container, false)

        mostrarData = view.findViewById(R.id.textViewMostrarData)
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate: String = simpleDateFormat.format(Date())
        mostrarData.text = currentDate

        val listaJogos : List<JogoItem> = fetchJogos()

        view.recyclerViewJogos.adapter = JogosAdapter(listaJogos)
        view.recyclerViewJogos.layoutManager = LinearLayoutManager(activity!!)
        arrayJogos = ArrayList()

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu2, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val c = Calendar.getInstance()
        val ano = c.get(Calendar.YEAR)
        val mes = c.get(Calendar.MONTH)
        val dia = c.get(Calendar.DAY_OF_MONTH)

        val id = item.itemId
        if(id == R.id.chat) {
            val intent = Intent(activity, ChatActivity::class.java)
            startActivity(intent)
            return true
        }
        if(id == R.id.data) {
            val dpd = DatePickerDialog(activity!!, { _, mAno, mMes, mDia ->
                mostrarData.text = "" + mDia + "/" + (mMes + 1) + "/" + mAno
            }, ano, mes, dia)
            dpd.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchJogos():List<JogoItem> {
        FirebaseFirestore.getInstance().collection("1Jornada")
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    for(document in it.result!!) {

                        val item = JogoItem(document.data.getValue(("Visitado").toString()) as String,
                            document.data.getValue(("Visitante").toString()) as String,
                            document.data.getValue(("Resultado").toString()) as String)

                        arrayJogos.add(item)
                    }
                }
            }
        return arrayJogos
    }

    companion object {
        fun newInstance(): ResultadosFragment = ResultadosFragment()
    }

}