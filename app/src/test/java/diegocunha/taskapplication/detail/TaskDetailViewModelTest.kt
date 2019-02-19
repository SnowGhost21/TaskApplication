package diegocunha.taskapplication.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import diegocunha.taskapplication.assertLiveDataEquals
import diegocunha.taskapplication.model.fixture.createTask
import diegocunha.taskapplication.model.repository.TaskRepository
import diegocunha.taskapplication.view.detail.TaskDetailViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TaskDetailViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val repository = mock<TaskRepository>()

    @Before
    fun setup() {
        whenever(repository.getTaskById(any())).thenReturn(Single.just(createTask))
    }

    @Test
    fun shouldGetTaskById() {
        val viewModel = TaskDetailViewModel(repository, "123")
        assertLiveDataEquals(createTask, viewModel.taskDetail)
    }
}