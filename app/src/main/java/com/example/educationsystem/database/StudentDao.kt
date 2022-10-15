package com.example.educationsystem.database

import androidx.room.*

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStudent(student: Student)

    @Query("SELECT * FROM student_table")
    fun getAllStudents(): List<Student>

    @Update(entity = Student::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)
}