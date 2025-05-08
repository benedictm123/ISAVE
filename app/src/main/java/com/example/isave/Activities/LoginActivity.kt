package com.example.isave.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.isave.DatabaseHelpers.UserDatabaseHelper
import com.example.isave.R
import com.example.isave.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var userDatabaseHelper: UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDatabaseHelper = UserDatabaseHelper(this)

        binding.goToRegister.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.LogInButton.setOnClickListener {
            val username = binding.UsernameEditText.text.toString().trim()
            val password = binding.PasswordEditText.text.toString().trim()
            val doesUserExist = userDatabaseHelper.getUsers(username,password)
            if(doesUserExist){
                Toast.makeText(this,"Log in Successful",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,HomeScreenActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Log in attempt failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}