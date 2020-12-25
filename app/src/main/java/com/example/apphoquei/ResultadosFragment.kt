package com.example.apphoquei

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class ResultadosFragment : Fragment() {

    lateinit var mostrarData: TextView

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {

        val view = inflater.inflate(R.layout.fragment_resultados, container, false)

        mostrarData = view.findViewById(R.id.textViewMostrarData)
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate: String = simpleDateFormat.format(Date())
        mostrarData.text = currentDate

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
            Toast.makeText(activity, "CHATTTTTTTTT", Toast.LENGTH_SHORT).show()
            return true
        }
        if(id == R.id.data) {
            val dpd = DatePickerDialog(activity!!, { _, mAno, mMes, mDia ->
                mostrarData.text = "" + mDia + "/" + (mMes+1) + "/" + mAno
            }, ano, mes, dia)
            dpd.show()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance(): ResultadosFragment = ResultadosFragment()
    }


}