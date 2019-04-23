package com.example.fitapp.Info

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.fitapp.MainActivity
import com.example.fitapp.R
import kotlinx.android.synthetic.main.activity_main.*

class InfoUnder : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_under)
        setSupportActionBar(toolbar)
    }

    fun GoBack (v: View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}