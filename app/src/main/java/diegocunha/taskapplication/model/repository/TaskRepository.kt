package diegocunha.taskapplication.model.repository

import diegocunha.taskapplication.model.data.ListTask
import diegocunha.taskapplication.model.data.Task
import io.reactivex.Single

interface TaskRepository {

    fun getTasks(): Single<ListTask>

    fun getTaskById(id: String): Single<Task>
}