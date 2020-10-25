package com.pamarcolino.sofie.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pamarcolino.sofie.R
import com.pamarcolino.sofie.viewmodel.NewTaskViewModel

class NewTaskFragment : Fragment() {

    companion object {
        fun newInstance() = NewTaskFragment()
    }

    private lateinit var viewModel: NewTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_taskragment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewTaskViewModel::class.java)
        // TODO: Use the ViewModel
    }

}