package diegocunha.taskapplication.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import diegocunha.taskapplication.extensions.asLiveData
import diegocunha.taskapplication.extensions.map
import diegocunha.taskapplication.model.repository.TaskRepository

class TaskDetailViewModel(private val repository: TaskRepository,
                          private val taskId: String) : ViewModel() {


    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val taskDetail = repository.getTaskById(taskId)
            .doOnSubscribe { _isLoading.postValue(true) }
            .doAfterTerminate { _isLoading.postValue(false) }
            .asLiveData()

    val image = taskDetail
            .map { it?.photo }

    val description = taskDetail
            .map { it?.description }

    val title = taskDetail
            .map { it?.title }


    class Factory(private val repository: TaskRepository) : ViewModelProvider.Factory {
        private lateinit var taskId: String

        fun setTaskId(id: String) {
            taskId = id
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TaskDetailViewModel(repository, taskId) as T
        }
    }

}