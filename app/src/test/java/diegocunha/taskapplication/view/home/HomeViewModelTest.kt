package diegocunha.taskapplication.view.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.timeout
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import diegocunha.taskapplication.assertLiveDataEquals
import diegocunha.taskapplication.model.fixture.createListTask
import diegocunha.taskapplication.model.repository.TaskRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val repository = mock<TaskRepository>()

    @Before
    fun setup() {
        whenever(repository.getTasks()).thenReturn(Single.just(createListTask))
    }


    @Test
    fun shouldGetTasks() {
        val viewModel = HomeViewModel(repository)
        assertLiveDataEquals(createListTask.tasksIds, viewModel.tasks)
    }

    @Test
    fun shouldUpdateLoadingStatus() {
        val observer = mock<Observer<Boolean>>()
        val viewModelTest = HomeViewModel(repository)
        viewModelTest.isLoading.observeForever(observer)

        verify(observer).onChanged(false)

    }

    @Test
    fun shouldUpdateErrorStatus() {
        val observer = mock<Observer<Boolean>>()
        val viewModelTest = HomeViewModel(repository)
        viewModelTest.error.observeForever(observer)

        verify(observer).onChanged(false)
    }

}