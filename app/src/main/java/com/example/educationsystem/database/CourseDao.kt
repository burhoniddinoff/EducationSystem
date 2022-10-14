package com.example.educationsystem.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCourse(course: Course)

    @Query("SELECT * FROM course_table")
    fun getAllCourse(): List<Course>
}