package com.example.educationsystem.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "course_table")
@Parcelize
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val info: String
) : Parcelable

@Entity(tableName = "student_table")
@Parcelize
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val lastName: String,
    val name: String,
    val middleName: String,/*
    val date: String,
    val group: String?,*/
) : Parcelable