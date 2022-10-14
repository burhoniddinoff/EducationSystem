package com.example.educationsystem.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.educationsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.kurslar.setOnClickListener {
            startActivity(Intent(this, CourseActivity::class.java))
        }
        binding.addStudent.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }
}