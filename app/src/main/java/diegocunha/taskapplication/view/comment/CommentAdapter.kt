package diegocunha.taskapplication.view.comment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import diegocunha.taskapplication.databinding.CommentItemBinding
import diegocunha.taskapplication.model.data.TaskComment
import diegocunha.taskapplication.view.databinding.ReactiveAdapter

class CommentAdapter : ReactiveAdapter<TaskComment, CommentItemBinding>() {

    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): CommentItemBinding {
        return CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(binding: CommentItemBinding, item: TaskComment) {
        val viewModel = CommentViewModel(item)
        binding.viewModel = viewModel
    }

    override fun calculateDiff(oldList: List<TaskComment>, newList: List<TaskComment>): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(CommentDiffCallback(oldList, newList))
    }
}