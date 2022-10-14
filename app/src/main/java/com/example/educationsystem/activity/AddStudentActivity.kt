package com.example.educationsystem.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.educationsystem.adapter.StudentAdapter
import com.example.educationsystem.database.Student
import com.example.educationsystem.database.StudentDatabase
import com.example.educationsystem.databinding.ActivityAddStudentBinding
import com.example.educationsystem.databinding.StudentAlertDialogBinding

class AddStudentActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddStudentBinding.inflate(layoutInflater) }
    private val studentDatabase by lazy { StudentDatabase.getInstance(this) }
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        studentAdapter = StudentAdapter()
        setUpRv()

        binding.back.setOnClickListener {
            finish()
        }
        binding.add.setOnClickListener {
            val binding = StudentAlertDialogBinding.inflate(LayoutInflater.from(this))
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setView(binding.root)
            binding.btnSave.setOnClickListener {
                val lastName = binding.editLastName.text.toString().trim()
                val name = binding.editName.text.toString().trim()
                val middleName = binding.editMiddleName.text.toString().trim()

                if (name.isNotBlank()) {
                    studentDatabase.dao().saveStudent(Student(0, lastName, name, middleName))
                    Toast.makeText(this, "Student saved!", Toast.LENGTH_SHORT).show()
                    setUpRv()
                    alertDialog.dismiss()
                } else {
                    Toast.makeText(this, "Enter data!", Toast.LENGTH_SHORT).show()
                }
                setUpRv()
            }
            setUpRv()
            alertDialog.show()
        }


    }

    private fun setUpRv() = binding.rv.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = studentAdapter
        studentAdapter.studentList = studentDatabase.dao().getAllStudents()
    }


}