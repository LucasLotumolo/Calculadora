package br.edu.ifsp.scl.ads.prdm.sc3039439.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3039439.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var currentInput: String = ""
    private var operator: String? = null
    private var firstNumber: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root) // agora usa o binding
    }

    fun numberAction(view: View) {
        val button = view as Button
        currentInput += button.text.toString()
        amb.resultsTV.text = currentInput
    }

    fun operationAction(view: View) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toDouble()
            operator = (view as Button).text.toString()
            currentInput = ""
            amb.resultsTV.text = "${firstNumber} $operator"
        }
    }

    fun equalsAction(view: View) {
        if (firstNumber == null || operator == null || currentInput.isEmpty()) return

        val secondNumber = currentInput.toDouble()
        var result: Double? = null

        when (operator) {
            "+" -> result = firstNumber!! + secondNumber
            "-" -> result = firstNumber!! - secondNumber
            "x" -> result = firstNumber!! * secondNumber
            "/" -> {
                if (secondNumber == 0.0) {
                    Toast.makeText(this, "Erro: não é possível dividir por zero!", Toast.LENGTH_SHORT).show()
                } else {
                    result = firstNumber!! / secondNumber
                }
            }
        }

        result?.let {
            amb.resultsTV.text = it.toString()
            resetCalc()
        }
    }

    fun allClearAction(view: View) {
        resetCalc()
        amb.resultsTV.text = ""
    }

    fun backSpaceAction(view: View) {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            amb.resultsTV.text = currentInput
        }
    }

    private fun resetCalc() {
        currentInput = ""
        operator = null
        firstNumber = null
    }
}
