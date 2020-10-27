package com.pamarcolino.sofie.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import androidx.core.widget.addTextChangedListener
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
import com.pamarcolino.sofie.databinding.NewTaskFragmentBinding
import com.pamarcolino.sofie.model.Task
import com.pamarcolino.sofie.util.AlertDialogUtil
import com.pamarcolino.sofie.util.StateView
import com.pamarcolino.sofie.viewmodel.NewTaskViewModel

class NewTaskFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(NewTaskViewModel::class.java)
    }

    private lateinit var binding: NewTaskFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.new_task_fragment, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_task, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuSave -> viewModel.save()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val task = arguments?.getParcelable("task") as? Task

        task?.also {
            viewModel.task = it
        }

        viewModel.isEditing = task != null

        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        binding.viewModel = viewModel

        viewModel.stateView.observe(viewLifecycleOwner) { state ->
            when(state){
                StateView.RUNNING -> {

                }
                StateView.SUCCESS -> {
                    AlertDialogUtil.show(
                        context = requireContext(),
                        title = activity?.getString(R.string.lbl_save) ?: "Save",
                        message = activity?.getString(R.string.lbl_task_save_successfull) ?: ""){
                       findNavController().navigateUp()
                    }
                }
                StateView.ERROR -> {
                    AlertDialogUtil.show(
                        context = requireContext(),
                        title = activity?.getString(R.string.lbl_error) ?: "Error",
                        message = viewModel.getMessageError()){
                        findNavController().navigateUp()
                    }
                }
                else -> { }
            }
        }

        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(TextUtils.isEmpty(s)){
                    binding.tilEmail.error = getString(R.string.required_field)
                    return
                }
                else
                    binding.tilEmail.error = null

                binding.tilEmail.error = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches())
                    getString(R.string.invalid_email)
                else
                    null
            }
        })

        binding.edtDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilDescription.error = if(TextUtils.isEmpty(s)) getString(R.string.required_field) else null
            }
        })

        binding.edtTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilTitle.error = if(TextUtils.isEmpty(s)) getString(R.string.required_field) else null
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }

}