package com.example.educationsystem.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.educationsystem.database.Course
import com.example.educationsystem.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityInfoBinding.inflate(layoutInflater) }
//    private val courseDatabase by lazy { CourseDatabase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val course = intent.getParcelableExtra<Course>("course")!!

        binding.textView.text = course.name
        binding.textInfo.text = course.info

        binding.back.setOnClickListener {
            finish()
        }
    }
}