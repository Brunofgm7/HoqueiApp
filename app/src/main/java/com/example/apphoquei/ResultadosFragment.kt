package com.example.apphoquei

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ResultadosFragment : Fragment() {

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_resultados, container, false)

    companion object {
        fun newInstance(): ResultadosFragment = ResultadosFragment()
    }
}