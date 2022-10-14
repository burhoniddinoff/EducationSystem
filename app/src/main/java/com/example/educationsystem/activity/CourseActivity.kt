package com.example.educationsystem.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.educationsystem.adapter.CourseAdapter
import com.example.educationsystem.database.Course
import com.example.educationsystem.database.CourseDatabase
import com.example.educationsystem.databinding.ActivityCourseBinding
import com.example.educationsystem.databinding.CourseAlertDialogBinding

class CourseActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCourseBinding.inflate(layoutInflater) }
    private val courseDatabase by lazy { CourseDatabase.getInstance(this) }
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        courseAdapter = CourseAdapter()
        setUpRv()

        binding.back.setOnClickListener {
            finish()
        }
        binding.add.setOnClickListener {
            val binding = CourseAlertDialogBinding.inflate(LayoutInflater.from(this))
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setView(binding.root)
            binding.add.setOnClickListener {
                val name = binding.editName.text.toString().trim()
                val info = binding.editInfo.text.toString().trim()
                if (name.isNotBlank() && info.isNotBlank()) {
                    courseDatabase.dao().saveCourse(Course(0, name, info))
                    Toast.makeText(this, "Kurs saved!", Toast.LENGTH_SHORT).show()
                    setUpRv()
                    alertDialog.dismiss()
                } else {
                    Toast.makeText(this, "Enter data!", Toast.LENGTH_SHORT).show()
                }
                setUpRv()
            }
            setUpRv()
            binding.back.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.show()
        }

        courseAdapter.onItemClicked = {
            val bundle = bundleOf("course" to it)
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

    }

    private fun setUpRv() = binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = courseAdapter
        courseAdapter.courseList = courseDatabase.dao().getAllCourse()

    }
}