package com.example.educationsystem.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Update
import com.example.educationsystem.R
import com.example.educationsystem.adapter.StudentAdapter
import com.example.educationsystem.database.Student
import com.example.educationsystem.database.StudentDatabase2
import com.example.educationsystem.databinding.ActivityAddStudentBinding
import com.example.educationsystem.databinding.StudentAlertDialogBinding
import com.example.educationsystem.databinding.StudentEditAlertDialogBinding
import com.example.educationsystem.databinding.StudentLayoutBinding

class AddStudentActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddStudentBinding.inflate(layoutInflater) }
    private val studentDatabase by lazy { StudentDatabase2.getInstance(this) }
    private lateinit var studentAdapter: StudentAdapter
    private var studentList: MutableList<Student> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        studentAdapter = StudentAdapter()
        setUpRv()

        binding.back.setOnClickListener {
            finish()
        }
        binding.btnSave.setOnClickListener {
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

        studentAdapter.onDeleteClicked = { student, pos ->
            AlertDialog.Builder(this).apply {
                setTitle("Delete")
                setIcon(R.drawable.delete)
                setMessage("Ushbu o’quvchini o’chirmoqchimisiz ?")
                setPositiveButton("Ha") { di, _ ->
                    studentDatabase.dao().deleteStudent(student)
                    studentAdapter.notifyItemRemoved(pos)
                    Toast.makeText(this@AddStudentActivity, "Student deleted", Toast.LENGTH_SHORT)
                        .show()
                    di.dismiss()
                    setUpRv()
                }
                setNeutralButton("Yo'q", null)
            }.create().show()
        }

        studentAdapter.onEditClicked = { student, pos ->
            editNote(student, pos)
        }

    }

    private fun editNote(student: Student, pos: Int){
        val bnE = StudentEditAlertDialogBinding.inflate(LayoutInflater.from(this))
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setView(bnE.root)
        bnE.apply {
            editName.setText(student.name)
            editLastName.setText(student.lastName)
            editMiddleName.setText(student.middleName)

            btnEdit.setOnClickListener {
                val name = bnE.editName.text.toString().trim()
                val lastName = bnE.editLastName.text.toString().trim()
                val middleName = bnE.editMiddleName.text.toString().trim()
                if (title.isNotBlank() && lastName.isNotBlank() && middleName.isNotEmpty()) {
                    studentDatabase.dao().updateStudent(Student(0, name, lastName, middleName))
                    Toast.makeText(this@AddStudentActivity, "Student edited!", Toast.LENGTH_SHORT).show()
                    setUpRv()
                    alertDialog.dismiss()
                } else {
                    Toast.makeText(this@AddStudentActivity, "Enter data!", Toast.LENGTH_SHORT).show()
                }
                setUpRv()
            }
        }
        alertDialog.show()
    }

    private fun setUpRv() = binding.rv.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = studentAdapter
        studentAdapter.studentList = studentDatabase.dao().getAllStudents()
    }
}