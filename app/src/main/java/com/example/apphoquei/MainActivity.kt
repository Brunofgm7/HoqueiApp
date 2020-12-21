package com.example.apphoquei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
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

        resultadosFragment = ResultadosFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, resultadosFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        setupNavigation(bottomNavigationView)

    }

    override fun onCreateOptionsMenu (menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.chat) {
            Toast.makeText(this, "CHATTTTTTTTT", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


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