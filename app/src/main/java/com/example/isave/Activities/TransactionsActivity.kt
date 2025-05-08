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
import com.example.isave.databinding.ActivityTransactionsBinding

class TransactionsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTransactionsBinding
    private lateinit var ExepensesAdapter: ExpenseAdapter
    private lateinit var expenseDatabaseHelper: ExpenseDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goHomeTransactions.setOnClickListener {
            val intent = Intent(this,HomeScreenActivity::class.java)
            startActivity(intent)
        }
        binding.newTransactions.setOnClickListener {
            val intent = Intent(this,AddTransactionScreen::class.java)
            startActivity(intent)
        }
        binding.goToCategoriesTransactions.setOnClickListener {
            val intent = Intent(this,Categories::class.java)
            startActivity(intent)
        }
        binding.goToMoreTransactions.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }

        expenseDatabaseHelper = ExpenseDatabaseHelper(this)
        ExepensesAdapter = ExpenseAdapter(expenseDatabaseHelper.getAllExpenses(),this)

        binding.transactionsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.transactionsRecyclerView.adapter =  ExepensesAdapter

    }
}