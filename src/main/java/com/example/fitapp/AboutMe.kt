package com.example.fitapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class AboutMe : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutme)
        setSupportActionBar(toolbar)
    }

    fun GoBack (v: View)
    {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}