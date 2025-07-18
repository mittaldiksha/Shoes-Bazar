package com.example.ecommerceappshoes.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecommerceappshoes.R
import com.example.ecommerceappshoes.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<TextView>(R.id.startBtn).setOnClickListener {
            Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
        }



    }
}