package com.example.educationsystem.database

import androidx.room.*

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStudent(student: Student)

    @Query("SELECT * FROM student_table")
    fun getAllStudents(): List<Student>
}