package com.example.educationsystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Course::class], version = 1)
abstract class CourseDatabase : RoomDatabase() {
    abstract fun dao(): CourseDao

    companion object {
        private var instance: CourseDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CourseDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    CourseDatabase::class.java,
                    "Course.db"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }

    }

}