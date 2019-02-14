package diegocunha.taskapplication.view.task

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import diegocunha.taskapplication.databinding.TaskItemBinding
import diegocunha.taskapplication.view.databinding.ReactiveAdapter

class TasksAdapter : ReactiveAdapter<String, TaskItemBinding>() {

    private val _onTaskClicked = MutableLiveData<TaskNavigationParams>()
    val onTaskClicked: LiveData<TaskNavigationParams> = _onTaskClicked

    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): TaskItemBinding {
        return TaskItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
    }

    override fun bind(binding: TaskItemBinding, item: String) {
        val viewModel = TaskItemViewModel(item)
        binding.viewModel = viewModel
        setClickListener(binding, item)

    }

    override fun calculateDiff(oldList: List<String>, newList: List<String>): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(TaskIdDiffCallback(oldList, newList))
    }

    private fun setClickListener(binding: TaskItemBinding, item: String) {
        val params = TaskNavigationParams(item)
        binding.card.setOnClickListener { _onTaskClicked.postValue(params) }
    }
}