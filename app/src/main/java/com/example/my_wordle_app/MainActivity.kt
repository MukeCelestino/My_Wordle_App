package com.example.my_wordle_app


import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.my_wordle_app.FourLetterWordList.FourLetterWordList.getRandomFourLetterWord


class MainActivity : AppCompatActivity() {

    private var wordToGuess = getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val word = findViewById<TextView>(R.id.word)
        word.text = wordToGuess
        val guessEditText = findViewById<EditText>(R.id.guess)

        val txt1 = findViewById<TextView>(R.id.txt1)
        val txt1c = findViewById<TextView>(R.id.txt1c)

        val txt2 = findViewById<TextView>(R.id.txt2)
        val txt2c = findViewById<TextView>(R.id.txt2c)

        val txt3 = findViewById<TextView>(R.id.txt3)
        val txt3c = findViewById<TextView>(R.id.txt3c)

        val button = findViewById<Button>(R.id.button)
        var i = 0

        button.setOnClickListener {

            val strValue = guessEditText.text.toString()
            if (i == 3){
                Toast.makeText(it.context,"You have exceeded your 3 guesses!!!", Toast.LENGTH_SHORT).show()
                word.visibility = View.VISIBLE
                button.text = "Reset"
                button.setOnClickListener {
                    txt1.text = ""
                    txt1c.text = ""
                    txt2.text = ""
                    txt2c.text = ""
                    txt3.text = ""
                    txt3c.text = ""

                }
            }
                if (i == 0) {
                    txt1.text = strValue
                    txt1c.text  = checkGuess(strValue.uppercase())

                }
                else if (i == 1) {
                    txt2.text = strValue
                    txt2c.text  = checkGuess(strValue.uppercase())

                }
                else if (i == 2) {
                    txt3.text = strValue
                    txt3c.text  = checkGuess(strValue.uppercase())
                }

            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
            i++
        }



    }
    private fun checkGuess(guess: String) : String {
        var result = ""

        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}