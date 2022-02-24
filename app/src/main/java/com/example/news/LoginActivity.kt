package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            nextActivity()
        }


        btnlogin.setOnClickListener {
            signIn()
        }
        signupbutton.setOnClickListener {
            register()
        }
    }

    private fun signIn() {
        val email = emaillogin.text.toString()
        val password = passwordlogin.text.toString()
        btnlogin.isEnabled = false
        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_SHORT).show()
            btnlogin.isEnabled = true
            return@signIn
        }
        else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@LoginActivity,
                            "You are Logged in",
                            Toast.LENGTH_SHORT
                        ).show()


                        val intent = Intent(this@LoginActivity,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user id", FirebaseAuth.getInstance().currentUser!!.uid)
                        intent.putExtra("email id", email)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT).show()
                        signupbutton.isEnabled = true
                    }
                }

            btnlogin.isEnabled = true
        }
    }


    private fun register() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun nextActivity() {
//        Log.i(TAG, "main activity")

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}