package com.example.apphoquei

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var resultadosFragment: ResultadosFragment
    lateinit var aovivoFragment: AoVivoFragment
    lateinit var campeonatosFragment: CampeonatosFragment
    lateinit var minhasequipasFragment: MinhasEquipasFragment
    lateinit var loginFragment: LoginFragment
    lateinit var mostrarData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btn_nav)

        resultadosFragment = ResultadosFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, resultadosFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        setupNavigation(bottomNavigationView)


//        mostrarData = findViewById(R.id.TextViewMostraData)
//        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
//        val currentDate: String = simpleDateFormat.format(Date())
//        mostrarData.text = currentDate


    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu2, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        val c = Calendar.getInstance()
//        val ano = c.get(Calendar.YEAR)
//        val mes = c.get(Calendar.MONTH)
//        val dia = c.get(Calendar.DAY_OF_MONTH)
//
//        val id = item.itemId
//        if(id == R.id.chat) {
//            Toast.makeText(this, "CHATTTTTTTTT", Toast.LENGTH_SHORT).show()
//            return true
//        }
//        if(id == R.id.data) {
//            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, mAno, mMes, mDia ->
//                mostrarData.setText("" + mDia + "/" + (mMes+1) + "/" + mAno)
//        }, ano, mes, dia)
//            dpd.show()
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun executarOutraActivity(outraActivity: Class<*>) {
        val x = Intent(this, outraActivity)
        startActivity(x)
    }

    private fun setupNavigation(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.resultados -> {
                    resultadosFragment = ResultadosFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, resultadosFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
                R.id.aovivo -> {
                    aovivoFragment = AoVivoFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, aovivoFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
                R.id.campeonatos -> {
                    campeonatosFragment = CampeonatosFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, campeonatosFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
                R.id.minhasequipas -> {
                    minhasequipasFragment = MinhasEquipasFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, minhasequipasFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
                R.id.login -> {
                    loginFragment = LoginFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, loginFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    true
                }
            }
            true
        }
    }
}