package diegocunha.taskapplication.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import diegocunha.taskapplication.extensions.asLiveData
import diegocunha.taskapplication.extensions.mutableLiveDataOf
import diegocunha.taskapplication.model.data.ListTask
import diegocunha.taskapplication.model.repository.TaskRepository

class HomeViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = mutableLiveDataOf(false)
    val error: LiveData<Boolean> = _error

    val tasks = repository.getTasks()
        .doOnSuccess { _isLoading.postValue(true) }
        .doAfterTerminate { _isLoading.postValue(false) }
        .onErrorReturnItem(ListTask(emptyList()))
        .doOnError { _error.postValue(true) }
        .map { it.tasksIds }
        .asLiveData()


}