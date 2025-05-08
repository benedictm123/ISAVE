package com.example.isave.DatabaseHelpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.isave.Classes.User

class UserDatabaseHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "user"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_ID INT PRIMARY KEY," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }
    fun createUser(user: User){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME,user.username)
            put(COLUMN_PASSWORD,user.password)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun getUsers(username:String,password:String):Boolean{
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username,password)
        val cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null)

        val userExists = cursor.count > 0
        return userExists
    }
}