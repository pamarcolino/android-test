package com.pamarcolino.sofie.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.pamarcolino.sofie.util.StateView
import com.pamarcolino.sofie.model.Task
import com.pamarcolino.sofie.network.ServiceApi
import com.pamarcolino.sofie.network.dto.parseToModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TasksViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
    private val compositeDisposable = CompositeDisposable()

    val tasks = mutableListOf<Task>()
    private var messageError = ""

    val stateView: MutableLiveData<StateView> by lazy {
        MutableLiveData<StateView>()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadTasks(){

        clearError()
        stateView.postValue(StateView.RUNNING)

        compositeDisposable.add(
            ServiceApi
            .dataService()
            .getTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                tasks.clear()

                tasks.addAll(
                    response.tasks.map { t -> t.parseToModel() }
                )

                stateView.postValue(StateView.SUCCESS)
                compositeDisposable.clear()
            }, { error ->
                messageError = error.message ?: ""
                stateView.postValue(StateView.ERROR)
            }))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        compositeDisposable.clear()
    }

    private fun clearError(){
        messageError = ""
    }

    fun getMessageError() = messageError

}