package com.example.apphoquei

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var resultadosFragment: ResultadosFragment
    lateinit var aovivoFragment: AoVivoFragment
    lateinit var campeonatosFragment: CampeonatosFragment
    lateinit var minhasequipasFragment: MinhasEquipasFragment
    lateinit var loginFragment: LoginFragment
    lateinit var perfilFragment: PerfilFragment
    private var backPressedTime: Long = 0
    lateinit var backToast:Toast


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btn_nav)
        val bottomNavigationViewLoggedin = findViewById<BottomNavigationView>(R.id.btn_nav_loggedin)

        resultadosFragment = ResultadosFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, resultadosFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        if(FirebaseAuth.getInstance().currentUser == null) {
            setupNavigation(bottomNavigationView)
            bottomNavigationView.visibility = View.VISIBLE
            bottomNavigationViewLoggedin.visibility = View.INVISIBLE
        } else {
            setupNavigation((bottomNavigationViewLoggedin))
            bottomNavigationView.visibility = View.INVISIBLE
            bottomNavigationViewLoggedin.visibility = View.VISIBLE
        }
    }


    private fun setupNavigation(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.resultados -> {
                    resultadosFragment = ResultadosFragment()
                    mudarFragment(resultadosFragment)

                }
                R.id.aovivo -> {
                    aovivoFragment = AoVivoFragment()
                    mudarFragment(aovivoFragment)

                }
                R.id.campeonatos -> {
                    campeonatosFragment = CampeonatosFragment()
                    mudarFragment(campeonatosFragment)

                }
                R.id.minhasequipas -> {
                    minhasequipasFragment = MinhasEquipasFragment()
                    mudarFragment(minhasequipasFragment)

                }
                R.id.login -> {
                    loginFragment = LoginFragment()
                    mudarFragment(loginFragment)

                }
                R.id.perfil -> {
                    perfilFragment = PerfilFragment()
                    mudarFragment(perfilFragment)
                }
            }
            true
        }
    }

    private fun mudarFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onBackPressed() {
        backToast = Toast.makeText(this, "Prima BACK novamente para sair.", Toast.LENGTH_SHORT)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            return
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }


}