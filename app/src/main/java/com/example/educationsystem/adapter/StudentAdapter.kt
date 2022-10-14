package com.example.educationsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.educationsystem.database.Course
import com.example.educationsystem.database.Student
import com.example.educationsystem.databinding.StudentLayoutBinding

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    lateinit var onItemClicked: (Student) -> Unit

    private val diffCallBack = object : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return newItem.name == oldItem.name
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var studentList: List<Student>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            StudentLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position])
    }

    override fun getItemCount(): Int = studentList.size

    inner class StudentViewHolder(private val binding: StudentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.apply {
                textName.text = "${student.name} ${student.lastName}"
                textLasName.text = student.middleName
            }
            itemView.setOnClickListener {
                onItemClicked(student)
            }
        }
    }

}