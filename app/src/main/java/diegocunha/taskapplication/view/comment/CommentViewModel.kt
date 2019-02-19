package diegocunha.taskapplication.view.comment

import diegocunha.taskapplication.model.data.TaskComment

class CommentViewModel(private val comment: TaskComment) {

    val imageUser = comment.photo

    val userName = comment.username

    val title = comment.title

    val description = comment.comment

    val note = comment.note.toFloat()
}