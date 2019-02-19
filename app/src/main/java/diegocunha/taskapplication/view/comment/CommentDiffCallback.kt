package diegocunha.taskapplication.view.comment

import androidx.recyclerview.widget.DiffUtil
import diegocunha.taskapplication.model.data.TaskComment

class CommentDiffCallback(private val oldList: List<TaskComment>, private val newList: List<TaskComment>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}