package diegocunha.taskapplication.model.fixture

import diegocunha.taskapplication.model.data.ListTask
import diegocunha.taskapplication.model.data.Task
import diegocunha.taskapplication.model.data.TaskComment

val createTaskComment = TaskComment(
        "photoUrl",
        "userName",
        "title",
        "comment",
        0.0
)

val createListTaskComment = listOf(createTaskComment)

val createTask = Task(
        "1",
        "city",
        "neighborhood",
        "photoUrl",
        "logoUrl",
        "phone",
        "title",
        "description",
        "address",
        0.0,
        0.0,
        createListTaskComment
)

val createListTask = ListTask(listOf("1"))