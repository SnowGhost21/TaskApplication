package diegocunha.taskapplication.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import diegocunha.taskapplication.extensions.mutableLiveDataOf
import diegocunha.taskapplication.model.data.ListTask
import diegocunha.taskapplication.model.repository.TaskRepository
import io.reactivex.disposables.Disposable

class HomeViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = mutableLiveDataOf(false)
    val error: LiveData<Boolean> = _error

    private val _tasks = MutableLiveData<List<String>>()
    val tasks: LiveData<List<String>> = _tasks

    private var requestTaskDisposable: Disposable? = null


    init {
        requestTasks()
    }

    private fun requestTasks() {
        requestTaskDisposable = repository.getTasks()
            .doOnSubscribe { _isLoading.postValue(true) }
            .doOnSuccess { _tasks.postValue(it.tasksIds) }
            .doAfterTerminate { _isLoading.postValue(false) }
            .onErrorReturnItem(ListTask(emptyList()))
            .doOnError { _error.postValue(true) }
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        requestTaskDisposable?.dispose()
    }


}