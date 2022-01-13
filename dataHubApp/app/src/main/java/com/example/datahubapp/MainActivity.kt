package com.example.datahubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize the bottom navigation view
        //create bottom navigation view object
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        toolbar?.title = "Toolbar"
        //toolbar?.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_menu_black_24dp)
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("Toolbar", "Back button pressed");
        //this.getSupportFragmentManager().popBackStack();
        //onBackPressed()
        return true
    }
}