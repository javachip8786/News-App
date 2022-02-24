package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_select_language.*

class SelectLanguage : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    // Create a new user with a first and last name
    val cities = db.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_language)

        var language = ""

        english.setOnClickListener{
            language = "English"
        }
        hindi.setOnClickListener{
            language = "Hindi"
        }
        marathi.setOnClickListener{
            language = "Marathi"
        }
        gujarati.setOnClickListener {
            language = "Gujarati"
        }
        punjabi.setOnClickListener {
            language = "Punjabi"
        }
        rajasthani.setOnClickListener {
            language = "Rajasthani"
        }
        bhojpuri.setOnClickListener {
            language = "Bhojpuri"
        }



        next.setOnClickListener{
            if(language == ""){
                Toast.makeText(this,"Select a Language",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                db.collection("users").document(Firebase.auth.currentUser!!.uid)
                    .update("language",language)
                val intent = Intent(this@SelectLanguage,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}