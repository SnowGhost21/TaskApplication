package diegocunha.taskapplication.view.task

import androidx.recyclerview.widget.DiffUtil
import diegocunha.taskapplication.model.data.Task
import kotlin.math.sign

class TaskIdDiffCallback(private val oldList: List<String>, private val newList: List<String>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newListSize.sign
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newListSize]
    }
}

data class TaskNavigationParams(val taskId: String)