package com.example.isave.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.isave.Adapters.ExpenseAdapter
import com.example.isave.DatabaseHelpers.ExpenseDatabaseHelper
import com.example.isave.R
import com.example.isave.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeScreenBinding
    private lateinit var ExpenseDb:ExpenseDatabaseHelper
    private lateinit var ExpenseAdapter:ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ExpenseDb = ExpenseDatabaseHelper(this)
        ExpenseAdapter = ExpenseAdapter(ExpenseDb.getAllExpenses(),this)

        binding.homeTransactionsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.homeTransactionsRecyclerView.adapter = ExpenseAdapter

        binding.addTransaction.setOnClickListener {
            val intent = Intent(this,AddTransactionScreen::class.java)
            startActivity(intent)
        }
        binding.HomeBtn.setOnClickListener {
            val intent = Intent(this,HomeScreenActivity::class.java)
            startActivity(intent)
        }
        binding.MoreBtn.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }

        binding.CategoriesBtn.setOnClickListener {
            val intent = Intent(this,Categories::class.java)
            startActivity(intent)
        }
        binding.TransactionsBtn.setOnClickListener {
            val intent = Intent(this,TransactionsActivity::class.java)
            startActivity(intent)
        }
    }
}