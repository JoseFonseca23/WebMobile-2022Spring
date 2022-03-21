package com.example.icp8

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        // Get the Intent that started this activity and extract the string
        val message = "Welcome " + intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }


    }

    fun logout(view: View) {
        Toast.makeText(this, "Logout Successful!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }
}