package diegocunha.taskapplication.model.repository.retrofit

import diegocunha.taskapplication.model.data.ListTask
import diegocunha.taskapplication.model.data.Task
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskApi {

    @GET("tarefa")
    fun getTasks(): Single<ListTask>

    @GET("tarefa/{id}")
    fun getTaskById(@Path("id") id: String): Single<Task>
}