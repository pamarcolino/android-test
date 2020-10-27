package com.pamarcolino.sofie.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.pamarcolino.sofie.model.Task
import com.pamarcolino.sofie.network.ServiceApi
import com.pamarcolino.sofie.network.dto.parseToDTO
import com.pamarcolino.sofie.util.StateView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Appendable
import java.util.*
import com.pamarcolino.sofie.R

class NewTaskViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    var isEditing = false
    val compositeDisposable = CompositeDisposable()

    private var messageError = ""

    val stateView: MutableLiveData<StateView> by lazy {
        MutableLiveData<StateView>()
    }

    var task = Task(
        _id = UUID.randomUUID(),
        _title = "",
        _description = "",
        _email = ""
    )

    fun save(){
        if(task.isValid()){
            if(isEditing)
                update()
            else
                saveNew()
        }
    }

    private fun saveNew(){

        stateView.postValue(StateView.RUNNING)

        compositeDisposable.add(
            ServiceApi
                .dataService()
                .saveTask(task.parseToDTO())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if(response.success)
                        stateView.postValue(StateView.SUCCESS)
                    else {
                        messageError = getApplication<Application>().getString(R.string.lbl_error_on_save)
                        stateView.postValue(StateView.ERROR)
                    }
                },{ error ->
                    messageError = error.message ?: "Error"
                    stateView.postValue(StateView.ERROR)
                })
        )
    }

    private fun update(){
        stateView.postValue(StateView.RUNNING)

        compositeDisposable.add(
            ServiceApi
                .dataService()
                .updateTask(task.id, task.parseToDTO())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if(response.success)
                        stateView.postValue(StateView.SUCCESS)
                    else {
                        messageError = getApplication<Application>().getString(R.string.lbl_error_on_save)
                        stateView.postValue(StateView.ERROR)
                    }
                },{ error ->
                    messageError = error.message ?: "Error"
                    stateView.postValue(StateView.ERROR)
                })
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        compositeDisposable.clear()
    }

    fun getMessageError() = messageError
}