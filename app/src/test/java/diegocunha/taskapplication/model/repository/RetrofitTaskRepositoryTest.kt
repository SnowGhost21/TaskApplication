package diegocunha.taskapplication.model.repository

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import diegocunha.taskapplication.model.fixture.createListTask
import diegocunha.taskapplication.model.fixture.createTask
import diegocunha.taskapplication.model.repository.retrofit.RetrofitTaskRepository
import diegocunha.taskapplication.model.repository.retrofit.TaskApi
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RetrofitTaskRepositoryTest {

    private val api = mock<TaskApi>()

    @Before
    fun setup() {
        whenever(api.getTasks()).thenReturn(Single.just(createListTask))
        whenever(api.getTaskById(any())).thenReturn(Single.just(createTask))
    }

    @Test
    fun shouldGetListOfTasks() {
        val repository = RetrofitTaskRepository(api)
        repository.getTasks()

        verify(api).getTasks()
    }

    @Test
    fun shouldGetTaskById() {
        val repository = RetrofitTaskRepository(api)
        repository.getTaskById("1")

        verify(api).getTaskById("1")
    }
}