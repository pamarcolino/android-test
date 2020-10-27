package com.pamarcolino.sofie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pamarcolino.sofie.R
import com.pamarcolino.sofie.databinding.TaskCardBinding
import com.pamarcolino.sofie.model.Task

class TasksAdapter(private val tasks: List<Task>
) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    private lateinit var binding: TaskCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent?.context),
            R.layout.task_card,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.count()

    class ViewHolder(private val binding: TaskCardBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.task = task
        }

    }

}