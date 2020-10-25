package com.pamarcolino.sofie.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pamarcolino.sofie.R
import com.pamarcolino.sofie.viewmodel.TasksViewModel

class TasksFragment : Fragment() {

    companion object {
        fun newInstance() = TasksFragment()
    }

    private lateinit var viewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tasks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
        // TODO: Use the ViewModel
    }

}