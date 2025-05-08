package com.example.isave.DatabaseHelpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.isave.Classes.Expense
import com.example.isave.Classes.MonthlyTotals
import com.example.isave.DatabaseHelpers.CategoriesDatabaseHelper.Companion

class ExpenseDatabaseHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "expensesDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "expensesTable"
        private const val COLUMN_ID ="id"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_AMOUNT = "amount"
        private const val COLUMN_PICTURE = "picture"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_ID INT PRIMARY KEY," +
                "$COLUMN_CATEGORY TEXT," +
                "$COLUMN_DESCRIPTION TEXT," +
                "$COLUMN_DATE TEXT," +
                "$COLUMN_AMOUNT DOUBLE," +
                "$COLUMN_PICTURE BLOB)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun addExpense(expense:Expense){

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CATEGORY,expense.category)
            put(COLUMN_DESCRIPTION,expense.description)
            put(COLUMN_DATE,expense.date)
            put(COLUMN_AMOUNT,expense.amount)
            put(COLUMN_PICTURE,expense.photoExpense)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun getAllExpenses():List<Expense>{
        val db = readableDatabase
        val expenses = mutableListOf<Expense>()
        val query = "SELECT * FROM $TABLE_NAME"

        val cursor = db.rawQuery(query,null)
         while (cursor.moveToNext()){
             val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
             val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))
             val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
             val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
             val amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))
             val picture = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_PICTURE))

             val expense = Expense(id,category,description,date,amount,picture)
             expenses.add(expense)
         }
        cursor.close()
        db.close()
        return expenses
    }

    fun getTotals(): List<MonthlyTotals>{
        val db = readableDatabase
        val totals = mutableListOf<MonthlyTotals>()

        val query = "SELECT $COLUMN_CATEGORY, SUM($COLUMN_AMOUNT) AS $COLUMN_AMOUNT" +
                " FROM $TABLE_NAME GROUP BY $COLUMN_CATEGORY"

        val cursor = db.rawQuery(query,null)

        var i = 0;
        while(cursor.moveToNext()){
            val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))
            val amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))

            val monthlyTotal = MonthlyTotals(i,category,amount)
            totals.add(monthlyTotal)
            i++
        }
        cursor.close()
        db.close()
        return totals
    }


}