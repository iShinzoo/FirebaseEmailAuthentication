package com.example.firebaseemailauthenticationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var btnSignup : Button
    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtEmail = findViewById(R.id.email)
        edtPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignup = findViewById(R.id.btn_signup)
        mAuth = Firebase.auth


        btnLogin.setOnClickListener(){
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            login(email, password)
        }

        btnSignup.setOnClickListener(){
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            SignedUp(email, password)
        }
    }

    private fun login(email : String, password : String){
        mAuth.signInWithEmailAndPassword(email , password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(
                        Intent(this,HomeActivity::class.java)
                    )
                    Toast.makeText(this, "Login Succesfull", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some Error", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun SignedUp(email : String, password : String){
        mAuth.createUserWithEmailAndPassword(email , password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "SignedUp Succesfully", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some Error", Toast.LENGTH_SHORT).show()
                }
            }
    }
}