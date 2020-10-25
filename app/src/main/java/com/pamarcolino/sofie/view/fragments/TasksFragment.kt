package com.pamarcolino.sofie.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.pamarcolino.sofie.R
import com.pamarcolino.sofie.databinding.TasksFragmentBinding
import com.pamarcolino.sofie.viewmodel.TasksViewModel

class TasksFragment : Fragment() {

    private lateinit var binding: TasksFragmentBinding
    private lateinit var viewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.tasks_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
        // TODO: Use the ViewModel
    }

}