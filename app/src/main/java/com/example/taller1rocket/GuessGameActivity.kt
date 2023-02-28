package com.example.taller1rocket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class GuessGameActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_game)
        val max = intent.getIntExtra("rang", 0)
        val txtGameTitle = findViewById<TextView>(R.id.titleGame)
        val txtGuess = findViewById<EditText>(R.id.txtGuess)
        val btnPlay : Button = findViewById(R.id.btnPlay)
        val txtCounter = findViewById<TextView>(R.id.counterTxt)
        val txtMessage = findViewById<TextView>(R.id.messageTxt)
        initialize(txtGameTitle, txtCounter, max)
        createClickListeners(btnPlay, txtGuess, txtCounter, txtMessage, max)
    }

    private fun initialize(pTxtTitle : TextView, pTxtCounter : TextView, pMax : Int)
    {
        val title = buildString {
            append(getString(R.string.GameTitleTxt))
            append(" ")
            append(pMax)
        }

        pTxtTitle.text = title
        pTxtCounter.text = getString(R.string.initCounterTxt)
    }

    private fun createClickListeners(pBtnPlay : Button, pTxtGuess : EditText, pTxtCounter: TextView, pTxtMessage: TextView, pMax : Int)
    {
        val randomNumber = (0..pMax).random()
        var counter = 0
        pBtnPlay.setOnClickListener {
            pTxtGuess.inputType = InputType.TYPE_CLASS_NUMBER
            val guess = pTxtGuess.text.toString()
            var guessInt = -1
            if(guess != "")
            {
                guessInt = guess.toInt()
            }
            if(guessInt < 0 || guessInt > pMax)
            {
                Toast.makeText(baseContext, getString(R.string.msgGameInvalidInput), Toast.LENGTH_LONG).show()
            }
            else
            {
                counter ++
                val msgCounter = buildString {
                    append(getString(R.string.counterTxt))
                    append(" ")
                    append(counter)
                }
                pTxtCounter.text = msgCounter
                if(guessInt == randomNumber)
                {
                    val msgGameOver = buildString {
                        append(getString(R.string.GameOverMsg))
                        append(" ")
                        append(counter)
                        append(" ")
                        append(getString(R.string.endGameoverMsg))
                    }
                    Toast.makeText(baseContext, msgGameOver, Toast.LENGTH_LONG).show()
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivity(intentMain)
                }
                else
                {
                    val msgMessage : String
                    if(guessInt > randomNumber)
                    {
                        msgMessage = buildString {
                            append(getString(R.string.messageTxt))
                            append(" ")
                            append(guessInt)
                            append(" ")
                            append(getString(R.string.msgGuessTooBig))
                        }
                        pTxtMessage.text = msgMessage
                    }
                    else
                    {
                        msgMessage = buildString {
                            append(getString(R.string.messageTxt))
                            append(" ")
                            append(guessInt)
                            append(" ")
                            append(getString(R.string.msgGuessTooSmall))
                        }
                        pTxtMessage.text = msgMessage
                    }
                }
            }
        }
    }
}