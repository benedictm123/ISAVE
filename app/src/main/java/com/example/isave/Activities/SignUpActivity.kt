package com.example.isave.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.isave.Classes.User
import com.example.isave.DatabaseHelpers.UserDatabaseHelper
import com.example.isave.R
import com.example.isave.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var userDatabase:UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userDatabase = UserDatabaseHelper(this)

        binding.SignUpButton.setOnClickListener {
            val username = binding.UsernameEditTextSignUp.text.toString().trim()
            val password = binding.PasswordEditTextSignUp.text.toString().trim()
            val passwordConfirm = binding.PasswordConfirmSignUp.text.toString().trim()

            if (password == passwordConfirm){
                val user = User(0,username,password)
                userDatabase.createUser(user)
                Toast.makeText(this,"Sign Up Successful",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Passwords don't match",Toast.LENGTH_SHORT).show()
            }
        }
    }
}