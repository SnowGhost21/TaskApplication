package diegocunha.taskapplication.model.repository.retrofit

import diegocunha.taskapplication.model.data.ListTask
import diegocunha.taskapplication.model.data.Task
import diegocunha.taskapplication.model.repository.TaskRepository
import io.reactivex.Single

class RetrofitTaskRepository(private val api: TaskApi): TaskRepository {

    override fun getTasks(): Single<ListTask> {
        return api.getTasks()
    }

    override fun getTaskById(id: String): Single<Task> {
        return api.getTaskById(id)
    }
}