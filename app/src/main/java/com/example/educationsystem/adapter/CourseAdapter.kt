package com.example.educationsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.educationsystem.database.Course
import com.example.educationsystem.databinding.CourseLayoutBinding

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    lateinit var onItemClicked: (Course) -> Unit

    private val diffCallBack = object : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return newItem.name == oldItem.name
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    var courseList: List<Course>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            CourseLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun getItemCount(): Int = courseList.size

    inner class CourseViewHolder(private val binding: CourseLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            binding.apply {
                textName.text = course.name
                textInfo.text = course.info
            }
            itemView.setOnClickListener {
                onItemClicked(course)
            }
        }
    }
}