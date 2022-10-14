package com.example.educationsystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Student::class], version = 4, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun dao(): StudentDao


    companion object {
        private var instance: StudentDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                        "PRIMARY KEY(`id`))")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")
            }
        }

        @Synchronized
        fun getInstance(context: Context): StudentDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    StudentDatabase::class.java,
                    "Student.db"
                ).allowMainThreadQueries()
                    .addMigrations(MIGRATION_2_3, (MIGRATION_1_2))
                    .build()
            }
            return instance!!
        }
    }


}