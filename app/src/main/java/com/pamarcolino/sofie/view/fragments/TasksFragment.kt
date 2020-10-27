package com.pamarcolino.sofie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pamarcolino.sofie.R
import com.pamarcolino.sofie.adapter.TasksAdapter
import com.pamarcolino.sofie.databinding.TasksFragmentBinding
import com.pamarcolino.sofie.model.Task
import com.pamarcolino.sofie.util.AlertDialogUtil
import com.pamarcolino.sofie.util.StateView
import com.pamarcolino.sofie.viewmodel.TasksViewModel

class TasksFragment : Fragment() {

    private lateinit var binding: TasksFragmentBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(TasksViewModel::class.java)
    }

    private var tasks: ArrayList<Task>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.tasks_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tasks = arguments?.getParcelableArrayList<Task>(TASKS_KEY)
        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.fabNewTask.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
        }

        val adapter = TasksAdapter(viewModel.tasks){ task ->
            val bundle = Bundle()
            bundle.putParcelable("task", task)
            findNavController().navigate(R.id.newTaskFragment, bundle)
        }

        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvTasks.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        viewModel.stateView.observe(viewLifecycleOwner) { state ->
            when(state){
                StateView.RUNNING -> {

                }
                StateView.SUCCESS -> {
                    binding.rvTasks.adapter?.notifyDataSetChanged()
                }
                StateView.ERROR -> {
                    AlertDialogUtil.show(
                        context = requireContext(),
                        title = activity?.getString(R.string.lbl_error) ?: "Error",
                        message = viewModel.getMessageError()){
                        activity?.finish()
                    }
                }
                else -> { }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }

    companion object {
        val TASKS_KEY = "tasks"
    }

}