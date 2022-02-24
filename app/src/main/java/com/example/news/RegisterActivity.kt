package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.news.models.user
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    // Create a new user with a first and last name
    val cities = db.collection("users")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loginbutton.setOnClickListener{
            login()
        }

        btnsignup.setOnClickListener {
            signup()
        }

    }

    private fun login() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun signup(){
        val email = emailsignup.text.toString()
        val password = passwordsignup.text.toString()
        val passwordc = confirmpassword.text.toString()
        val name = namesignup.text.toString();
        var language = ""

//            regBotton.isEnabled = false
        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_SHORT).show()
            return@signup
        }

        if(password != passwordc){
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            return@signup
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(
                        this@RegisterActivity,
                        "You are Registered",
                        Toast.LENGTH_SHORT
                    ).show()


                    val data1 = user(Firebase.auth.currentUser!!.uid,name,email,language,password)

                    data1.uid?.let { it1 -> cities.document(it1).set(data1) }


                    val intent = Intent(this@RegisterActivity,SelectLanguage::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,
                        task.exception!!.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}