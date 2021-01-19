package com.example.apphoquei

import android.app.DatePickerDialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_resultados.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ResultadosFragment : Fragment() {

    lateinit var mostrarData: TextView
    lateinit var textViewNaoHaJogos : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {

        val view = inflater.inflate(R.layout.fragment_resultados, container, false)

        mostrarData = view.findViewById(R.id.textViewMostrarData)
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate: String = simpleDateFormat.format(Date())
        mostrarData.text = currentDate


        textViewNaoHaJogos = view.findViewById(R.id.textViewNaoHaJogos)
        textViewNaoHaJogos.isVisible = false

        fetchJogos {
            if(it.size == 0) {
                view.recyclerViewJogos.adapter = JogosAdapter(it)
                view.recyclerViewJogos.layoutManager = LinearLayoutManager(activity!!)
                textViewNaoHaJogos.isVisible = true
            } else {
                view.recyclerViewJogos.adapter = JogosAdapter(it)
                view.recyclerViewJogos.layoutManager = LinearLayoutManager(activity!!)
                textViewNaoHaJogos.isVisible = false
            }
        }

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
                if(mDia < 10) {
                    if(mMes + 1 in 1..9) {
                        mostrarData.text = "0" + mDia + "/" + "0" + (mMes + 1) + "/" + mAno
                        fetchJogos {
                            if(it.size == 0) {
                                view?.recyclerViewJogos?.adapter = JogosAdapter(it)
                                view?.recyclerViewJogos?.layoutManager = LinearLayoutManager(activity!!)
                                textViewNaoHaJogos.isVisible = true
                            } else {
                                view?.recyclerViewJogos?.adapter = JogosAdapter(it)
                                view?.recyclerViewJogos?.layoutManager = LinearLayoutManager(activity!!)
                                textViewNaoHaJogos.isVisible = false
                            }
                        }
                    } else {
                        mostrarData.text = "0" + mDia + "/" + (mMes + 1) + "/" + mAno
                        fetchJogos {
                            if(it.size == 0) {
                                textViewNaoHaJogos.isVisible = true
                                view?.recyclerViewJogos?.adapter = JogosAdapter(it)
                                view?.recyclerViewJogos?.layoutManager = LinearLayoutManager(activity!!)
                            } else {
                                view?.recyclerViewJogos?.adapter = JogosAdapter(it)
                                view?.recyclerViewJogos?.layoutManager = LinearLayoutManager(activity!!)
                                textViewNaoHaJogos.isVisible = false
                            }
                        }
                    }
                } else {
                    if(mMes + 1 in 1..9) {
                        mostrarData.text = "" + mDia + "/" + "0" + (mMes + 1) + "/" + mAno
                        fetchJogos {
                            if(it.size == 0) {
                                textViewNaoHaJogos.isVisible = true
                                view?.recyclerViewJogos?.adapter = JogosAdapter(it)
                                view?.recyclerViewJogos?.layoutManager = LinearLayoutManager(activity!!)
                            } else {
                                view?.recyclerViewJogos?.adapter = JogosAdapter(it)
                                view?.recyclerViewJogos?.layoutManager = LinearLayoutManager(activity!!)
                                textViewNaoHaJogos.isVisible = false
                            }
                        }
                    } else {
                        mostrarData.text = "" + mDia + "/" + (mMes + 1) + "/" + mAno
                        fetchJogos {
                            if(it.size == 0) {
                                textViewNaoHaJogos.isVisible = true
                                view?.recyclerViewJogos?.adapter = JogosAdapter(it)
                                view?.recyclerViewJogos?.layoutManager = LinearLayoutManager(activity!!)
                            } else {
                                view?.recyclerViewJogos?.adapter = JogosAdapter(it)
                                view?.recyclerViewJogos?.layoutManager = LinearLayoutManager(activity!!)
                                textViewNaoHaJogos.isVisible = false
                            }
                        }
                    }
                }

            }, ano, mes, dia)
            dpd.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchJogos(myCallback: (ArrayList<JogoItem>) -> Unit) {
        var arrayJogos = ArrayList<JogoItem>()

        FirebaseFirestore.getInstance().collection("1Jornada")
                .get()
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        for(document in it.result!!) {
                            if(document.data.getValue(("Data").toString()) == mostrarData.text.toString()) {
                                val item = JogoItem(document.data.getValue(("Visitado").toString()) as String,
                                        document.data.getValue(("Visitante").toString()) as String,
                                        document.data.getValue(("Resultado").toString()) as String)

                                arrayJogos.add(item)
                            }
                        }
                    }
                }

        FirebaseFirestore.getInstance().collection("2Jornada")
                .get()
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        for(document in it.result!!) {
                            if(document.data.getValue(("Data").toString()) == mostrarData.text.toString()) {
                                val item = JogoItem(document.data.getValue(("Visitado").toString()) as String,
                                        document.data.getValue(("Visitante").toString()) as String,
                                        document.data.getValue(("Resultado").toString()) as String)

                                arrayJogos.add(item)

                            }
                        }
                        myCallback(arrayJogos)
                    }
                }
    }

    companion object {
        fun newInstance(): ResultadosFragment = ResultadosFragment()
    }

}