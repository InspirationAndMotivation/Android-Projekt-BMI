package bmi

import java.math.BigDecimal

class BMICalculator (var mass: Double, var height: Double) : BMI
{

    override fun countBMI(): Double {
        var bmi: Double = (mass*10000.0/(height*height))
        bmi = (BigDecimal.valueOf(bmi).setScale(2,BigDecimal.ROUND_HALF_DOWN)).toDouble() //two places after comma
        return bmi
    }

}