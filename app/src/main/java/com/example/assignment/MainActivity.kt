package com.example.assignment

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.BoringLayout
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

    private var emailEditText: EditText? = null
    private var messageEditText: EditText? = null
    private var sendButton: Button? = null
    private var emailErrorMessage: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setEmailTextWatcher()
        setListener()
    }
    private fun initViews() {
        emailEditText = findViewById(R.id.email)
        messageEditText = findViewById(R.id.message)
        sendButton = findViewById(R.id.send_button)
        emailErrorMessage = findViewById(R.id.email_errorText)
    }

    private fun setEmailTextWatcher() {
        emailEditText?.doAfterTextChanged { email ->
            if(validateEmail(email.toString())) {
                handleUiEvents(emailErrorMessage, emailEditText, true)

            } else {
                handleUiEvents(emailErrorMessage, emailEditText, false)
            }
        }
    }


    private fun handleUiEvents(textView: TextView?, editText: EditText?, areInputsCorrect: Boolean) {
        sendButton?.isEnabled = areInputsCorrect
        setViewColor(editText, if (areInputsCorrect) R.color.black else R.color.tomato_red)
        textView?.isVisible = !areInputsCorrect
    }

    private fun validateEmail(email: String?): Boolean {
        return email?.contains("@") == true
    }

    private fun setViewColor(view: View?, color: Int) {
        when(view) {
            is EditText -> {
                val drawable = view.background as GradientDrawable
                drawable.setStroke(1, ContextCompat.getColor(this, color))
            }
        }
    }

    private fun setListener () {
        sendButton?.setOnClickListener{
            val intent = Intent(this, ReceiverActivity::class.java)
            intent.putExtra("email", emailEditText?.text.toString())
            intent.putExtra("message", messageEditText?.text.toString())
            startActivity(intent)
        }
    }

}