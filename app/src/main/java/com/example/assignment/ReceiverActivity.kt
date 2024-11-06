package com.example.assignment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ReceiverActivity : AppCompatActivity() {
    private var emailEditText: EditText? = null
    private var messageEditText: EditText? = null
    private var clearButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver)
        initViews()
        handleExtras()
        setListener()

    }
    private fun initViews() {
        emailEditText = findViewById(R.id.email)
        messageEditText = findViewById(R.id.message)
        clearButton = findViewById(R.id.clear)
    }

    private fun handleExtras() {
        val extras = intent.extras
        extras.let {
            setTexts(it?.getString("email"), it?.getString("message"))
        }
    }

    private fun setTexts(email: String?, message: String?) {

        email?.let { emailEditText?.setText(it) }
        message?.let { messageEditText?.setText(it) }
    }

    private fun setListener() {
        clearButton?.let { button ->
            button.setOnClickListener {
                emailEditText?.text?.clear()
                messageEditText?.text?.clear()
            }
        }
    }
}