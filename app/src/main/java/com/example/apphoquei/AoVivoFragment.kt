package com.example.apphoquei

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AoVivoFragment : Fragment() {
    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_ao_vivo, container, false)

    companion object {
        fun newInstance(): AoVivoFragment = AoVivoFragment()
    }
}