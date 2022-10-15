package com.example.educationsystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase2 : RoomDatabase() {

    abstract fun dao(): StudentDao

    companion object {
        private var instance: StudentDatabase2? = null

        @Synchronized
        fun getInstance(context: Context): StudentDatabase2 {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    StudentDatabase2::class.java,
                    "Student2.db"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}