package com.example.fitapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity;
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import bmi.BMICalculator
import java.lang.Double


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        InputText1.setRawInputType(InputType.TYPE_CLASS_NUMBER) //number keyboard
        InputText2.setRawInputType(InputType.TYPE_CLASS_NUMBER)
    }

    fun ButtCount (v: View)
    {
        CountKgCm ()
    }

    fun ViewBmi(bmi:kotlin.Double)
    {

        findViewById<TextView>(R.id.textView).text = bmi.toString()

        when
        {
            bmi<18.5 ->  {
                findViewById<TextView>(R.id.textView2).text = "UNDERWEIGHT"
                findViewById<TextView>(R.id.textView).setTextColor(-16734573)
                findViewById<TextView>(R.id.textView2).setTextColor(-16734573)
                button2.visibility = View.VISIBLE
            }
            18.5<bmi && bmi<=24.9 ->
            {
                findViewById<TextView>(R.id.textView2).text = "NORMAL"
                findViewById<TextView>(R.id.textView).setTextColor(-134085643)
                findViewById<TextView>(R.id.textView2).setTextColor(-134085643)
                button2.visibility = View.VISIBLE
            }
            25<bmi && bmi<=29.9 -> {
                findViewById<TextView>(R.id.textView2).text = "OVERWEIGHT"
                findViewById<TextView>(R.id.textView).setTextColor(-15998453)
                findViewById<TextView>(R.id.textView2).setTextColor(-15998453)
                button2.visibility = View.VISIBLE
            }
            30<bmi && bmi<=39.9 -> {
                findViewById<TextView>(R.id.textView2).text = "OBESE"
                findViewById<TextView>(R.id.textView).setTextColor(-663512)
                findViewById<TextView>(R.id.textView2).setTextColor(-663512)
                button2.visibility = View.VISIBLE
            }
            bmi>40 ->
            {
                findViewById<TextView>(R.id.textView2).text = "EXTREMALY OBESE"
                findViewById<TextView>(R.id.textView).setTextColor(-4718592)
                findViewById<TextView>(R.id.textView2).setTextColor(-4718592)
                button2.visibility = View.VISIBLE
            }
        }
    }

    fun CountLbsInch ()
    {
        val mass = getMass()
        val height = getHeight()

        var bmi = (BMICalculator(mass, height).countBMI() * 703)
        ViewBmi(bmi)
    }

    fun CountKgCm ()
    {
        val mass = getMass()
        val height = getHeight()

            var bmi = BMICalculator(mass, height).countBMI()
            ViewBmi(bmi)
    }
    fun getMass () :kotlin.Double
    {
        val textInputMass = findViewById<TextInputEditText>(R.id.InputText1)
        if (!textInputMass.text.toString().equals("") && (!textInputMass.text.toString().equals("0")))
        {
            val mass = Double.parseDouble(textInputMass.text.toString())
            return mass
        } else {
            return 0.0
            Toast.makeText(applicationContext, "Enter mass [lbs] and height [inch]!", Toast.LENGTH_SHORT).show()
        }
    }

    fun getHeight ():kotlin.Double
    {
        val textInputHeight = findViewById<TextInputEditText>(R.id.InputText2)
        if ( !textInputHeight.text.toString().equals("")  && !textInputHeight.text.toString().equals("0") )
        {
            val height = Double.parseDouble(textInputHeight.text.toString())
            return height
        } else {
        return 0.0
        Toast.makeText(applicationContext, "Enter mass [lbs] and height [inch]!", Toast.LENGTH_SHORT).show()
                }
    }

    fun ButtInfo (v:View)
    {
        var bmi = Double.parseDouble(findViewById<TextView>(R.id.textView).text.toString())


        when
        {
            bmi<18.5 ->  {
                val intent1 = Intent(this,InfoUnder::class.java)
                startActivity(intent1)
            }
            18.5<bmi && bmi<=24.9 ->
            {
                val intent2 = Intent(this,InfoNormal::class.java)
                startActivity(intent2)
            }
            25<bmi && bmi<=29.9 -> {
                val intent3 = Intent(this,InfoOver::class.java)
                startActivity(intent3)
            }
            30<bmi && bmi<=39.9 -> {
                val intent4 = Intent(this,InfoObese::class.java)
                startActivity(intent4)
            }
            bmi>40 ->
            {
                val intent5 = Intent(this,InfoExtraObese::class.java)
                startActivity(intent5)
            }
        }
    }

    //Menu with 3 dots
    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when (item.itemId) {
            R.id.action_aboutme ->
            {
                Toast.makeText(applicationContext,"New achievement!",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AboutMe::class.java)
                startActivity(intent)
                true
            }
            R.id.action_changeunits ->
            {
                //findViewById<TextInputEditText>(R.id.InputText1).hint = "Enter your mass [lbs]"
               // findViewById<TextInputEditText>(R.id.InputText2).hint = "Enter your height [inch]"
               CountLbsInch()
                true
            }
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

