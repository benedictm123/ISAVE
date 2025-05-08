package com.example.isave.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.isave.Adapters.MonthlyCategoryTotalsAdapter
import com.example.isave.DatabaseHelpers.ExpenseDatabaseHelper
import com.example.isave.R
import com.example.isave.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMenuBinding
    private lateinit var ExpenseDb :ExpenseDatabaseHelper
    private lateinit var monthlyAdapter:MonthlyCategoryTotalsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.HomeCat.setOnClickListener {
            val intent = Intent(this,HomeScreenActivity::class.java)
            startActivity(intent)
        }
        binding.TrasactionsCat.setOnClickListener {
            val intent = Intent(this,TransactionsActivity::class.java)
            startActivity(intent)
        }
        binding.newTransactionsCat.setOnClickListener {
            val intent = Intent(this,AddTransactionScreen::class.java)
            startActivity(intent)
        }
        binding.categoriesCat.setOnClickListener {
            val intent = Intent(this,Categories::class.java)
            startActivity(intent)
        }

        ExpenseDb = ExpenseDatabaseHelper(this)
        monthlyAdapter = MonthlyCategoryTotalsAdapter(ExpenseDb.getTotals(),this)

        binding.categoryTotalsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.categoryTotalsRecyclerView.adapter = monthlyAdapter
    }
}