package com.example.icp8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

const val EXTRA_MESSAGE = "com.example.icp8.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user taps the Send button */
    fun sendMessage(view: View) {
        val username = findViewById<TextView>(R.id.editTextTextPersonName)
        val password = findViewById<TextView>(R.id.editTextTextPassword2)
        if((username.text.toString().equals("Admin") && password.text.toString().equals("password")) || (username.text.toString().equals("jrfvd6") && password.text.toString().equals("test"))){
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
            val editText = findViewById<EditText>(R.id.editTextTextPersonName)
            val message = editText.text.toString()
            val intent = Intent(this, DisplayMessageActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
