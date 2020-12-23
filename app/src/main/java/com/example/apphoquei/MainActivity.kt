package com.example.apphoquei

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var resultadosFragment: ResultadosFragment
    lateinit var aovivoFragment: AoVivoFragment
    lateinit var campeonatosFragment: CampeonatosFragment
    lateinit var minhasequipasFragment: MinhasEquipasFragment
    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btn_nav)
//        val bottomNavigationViewLoggedin = findViewById<BottomNavigationView>(R.id.btn_nav_loggedin)

        resultadosFragment = ResultadosFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, resultadosFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        setupNavigation(bottomNavigationView)

    }


    //adicionar if logged in
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
                }
                R.id.aovivo -> {
                    aovivoFragment = AoVivoFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, aovivoFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.campeonatos -> {
                    campeonatosFragment = CampeonatosFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, campeonatosFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.minhasequipas -> {
                    minhasequipasFragment = MinhasEquipasFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, minhasequipasFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.login -> {
                    loginFragment = LoginFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, loginFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }
    //else (se nao tiver login feito)
    //desativar a bottom navigation view antes de ter sessão e ativar a outra bottom nav view (visible/invisible)



    //adicionar esta função para dar double back para sair da aplicação

//    private var doubleBackToExitPressedOnce = false
//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed()
//            finishAffinity()
//            return
//        }
//
//        this.doubleBackToExitPressedOnce = true
//        Toast.makeText(this, "BACK novamente para sair", Toast.LENGTH_SHORT).show()
//
//
//        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
//    }



    //    Apagar se nao for usada
    private fun executarOutraActivity(outraActivity: Class<*>) {
        val x = Intent(this, outraActivity)
        startActivity(x)
    }

}