package com.example.fitapp


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import bmi.BmiAmericanCalc
import bmi.BmiStandardCalc
import com.example.fitapp.Info.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Double

class MainActivity : AppCompatActivity() {

        private var unitsCheck = false
        private var bmi = 0.0
        private var mass = 0.0
        private var height = 0.0
        private var description=""
        private var color = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        InputText1.setRawInputType(InputType.TYPE_CLASS_NUMBER) //number keyboard
        InputText2.setRawInputType(InputType.TYPE_CLASS_NUMBER)
        }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean("Units",unitsCheck)
        outState?.putDouble("BMIndex",bmi)
        outState?.putDouble("UsersMass",mass)
        outState?.putDouble("UsersHeight",height)
        outState?.putString("BMIDescrip",description)
        outState?.putInt("Color", color)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        unitsCheck=savedInstanceState!!.getBoolean("Units")
        //changeUnits()
        bmi = savedInstanceState.getDouble("BMIndex")
        mass = savedInstanceState.getDouble("UsersMass")
        height = savedInstanceState.getDouble("UsersHeight")
            findViewById<TextView>(R.id.InputText1).text = mass.toString()
            findViewById<TextView>(R.id.InputText2).text = height.toString()
        findViewById<TextView>(R.id.textView1).text = bmi.toString()
        description =  savedInstanceState.getString("BMIDescrip")
            color = savedInstanceState.getInt("Color")
            setView(description, color)
        viewBmi(bmi)
        super.onRestoreInstanceState(savedInstanceState)
    }

    fun ButtCount (v: View)
    {
        val textInputMass = findViewById<TextInputEditText>(R.id.InputText1)
        val textInputHeight = findViewById<TextInputEditText>(R.id.InputText2)

         if (checkData()) {
             mass = Double.parseDouble(textInputMass.text.toString())
             height = Double.parseDouble(textInputHeight.text.toString())

             if (unitsCheck) {
                 setBMI(BmiAmericanCalc(mass, height).countBMI())
             } else {
                 setBMI(BmiStandardCalc(mass, height).countBMI())
             }
             setDescription(getBMI())
             setColor(getBMI())
             findViewById<TextView>(R.id.textView1).text = getBMI().toString()
             viewBmi(getBMI())
         }
        else
         {
            // Toast.makeText(applicationContext, "Enter mass and height!", Toast.LENGTH_SHORT).show()

             findViewById<TextView>(R.id.textView1).text = ""
             findViewById<TextView>(R.id.textView2).text = ""
             setBMI(0.0)
         }
    }


    private fun viewBmi(bmi_val: kotlin.Double)
    {
            findViewById<TextView>(R.id.textView2).text = getDescription()
        findViewById<TextView>(R.id.textView1).setTextColor(getColor())
        findViewById<TextView>(R.id.textView2).setTextColor(getColor())
        button2.visibility = View.VISIBLE
    }

    private fun setDescription(val_bmi: kotlin.Double)
    {
        when
        {
            val_bmi<18.5 ->  {
                description = ("UNDERWEIGHT")
            }
            18.5<val_bmi && val_bmi<=24.9 ->
            {
                description = ("NORMAL")
            }
            25<val_bmi && val_bmi<=29.9 -> {
                description = ("OVERWEIGHT")
            }
            30<val_bmi && val_bmi<=39.9 -> {
                description = ("OBESE")          }
            val_bmi>40 ->
            {
                description = ("EXTREMALY OBESE")
            }
        }

    }
    private fun setColor(val_bmi: kotlin.Double)
    {
        when
        {
            val_bmi<18.5 ->  {
                color = -16734573
            }
            18.5<val_bmi && val_bmi<=24.9 ->
            {
                color = -134085643
            }
            25<val_bmi && val_bmi<=29.9 -> {
                color = -15998453
            }
            30<val_bmi && val_bmi<=39.9 -> {
                color = -663512
            }
            val_bmi>40 ->
            {
                color = -4718592
            }
        }
    }
    private fun setView (descrip :String, col:Int)
    {
        description = descrip
        color = col
    }
    private fun getColor ():Int{
        return color
    }
    private fun getDescription():String
    {
        return description
    }

    private fun setBMI(val_bmi: kotlin.Double) {
        bmi = val_bmi
    }

    private fun getBMI(): kotlin.Double {
        return bmi
    }

    private fun checkData() : Boolean{

        val textInputMass = findViewById<TextInputEditText>(R.id.InputText1)
        val textInputHeight = findViewById<TextInputEditText>(R.id.InputText2)

        var check = true
        if (textInputMass.text.toString().equals(""))
        {
            check = false
            Toast.makeText(applicationContext, "Enter mass!", Toast.LENGTH_SHORT).show()
        } else if (textInputMass.text.toString().equals("0"))
        {
            check = false
            Toast.makeText(applicationContext, "Mass can`t be 0", Toast.LENGTH_SHORT).show()
        } else if (textInputHeight.text.toString().equals(""))
        {
            check = false
            Toast.makeText(applicationContext, "Enter height!", Toast.LENGTH_SHORT).show()
        } else if (textInputHeight.text.toString().equals("0"))
        {
            check = false
            Toast.makeText(applicationContext, "Height can`t be 0", Toast.LENGTH_SHORT).show()
        } else if (!unitsCheck &&(0>Double.parseDouble(textInputHeight.text.toString()) || Double.parseDouble(textInputHeight.text.toString())>400))
        {
            check = false
            Toast.makeText(applicationContext, "Height is impossible number", Toast.LENGTH_SHORT).show()
        } else if (!unitsCheck &&(0>Double.parseDouble(textInputMass.text.toString()) || Double.parseDouble(textInputMass.text.toString())>400))
        {
            check = false
            Toast.makeText(applicationContext, "Mass is impossible number", Toast.LENGTH_SHORT).show()
        } else if (unitsCheck &&(0>Double.parseDouble(textInputHeight.text.toString()) || Double.parseDouble(textInputHeight.text.toString())>400))
        {
            check = false
            Toast.makeText(applicationContext, "Height is impossible number", Toast.LENGTH_SHORT).show()
        } else if (unitsCheck &&(0>Double.parseDouble(textInputMass.text.toString()) || Double.parseDouble(textInputMass.text.toString())>885))
        {
            check = false
            Toast.makeText(applicationContext, "Mass is impossible number", Toast.LENGTH_SHORT).show()
        }
            return check
    }

    private fun checkUnits() :Boolean {
        val textInputMass = findViewById<TextInputEditText>(R.id.InputText1)
        val textInputHeight = findViewById<TextInputEditText>(R.id.InputText2)

        if (unitsCheck == true) {
            textMass.text = "Mass [lbs]"
            textHeight.text = "Height [inch]"
             } else {
            textMass.text = "Mass [kg]"
            textHeight.text = "Height [cm]"
             }
        //clear of data
        textInputMass.text = null
        textInputHeight.text = null
        findViewById<TextView>(R.id.textView1).text = ""
        findViewById<TextView>(R.id.textView2).text = ""
        setBMI(0.0)

        return unitsCheck
    }

    fun ButtInfo (v:View)
    {
        bmi = getBMI()
        when
        {
            bmi<18.5 ->  {
                val intent1 = Intent(this, InfoUnder::class.java)
                startActivity(intent1)
            }
            18.5<bmi && bmi<=24.9 ->
            {
                val intent2 = Intent(this, InfoNormal::class.java)
                startActivity(intent2)
            }
            25<bmi && bmi<=29.9 -> {
                val intent3 = Intent(this, InfoOver::class.java)
                startActivity(intent3)
            }
            30<bmi && bmi<=39.9 -> {
                val intent4 = Intent(this, InfoObese::class.java)
                startActivity(intent4)
            }
            bmi>40 ->
            {
                val intent5 = Intent(this, InfoExtraObese::class.java)
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
                if (unitsCheck==true) {
                    unitsCheck = false
                    item.isChecked = true
                    checkUnits()
                } else {
                    unitsCheck = true
                    item.isChecked = false
                    checkUnits()
                }
                true
            }
            /*R.id.list_history ->
            {
                val intent = Intent (this, ListHistory::class.java)
                startActivity(intent)
                true
            } */
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

