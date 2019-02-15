package diegocunha.taskapplication.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import diegocunha.taskapplication.R
import diegocunha.taskapplication.databinding.FragmentHomeBinding
import diegocunha.taskapplication.view.MainActivity
import diegocunha.taskapplication.view.task.TaskNavigationParams
import diegocunha.taskapplication.view.task.TasksAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.home_title)
            setDisplayHomeAsUpEnabled(false)
        }

        val adapter = TasksAdapter()
        adapter.onTaskClicked.observe(this, Observer(this::navigateToTaskDetail))

        binding.tasksRecyclerView.adapter = adapter

        viewModel.tasks.observe(this, Observer {
            it?.let { tasks -> adapter.setItems(tasks) }
        })

        return binding.root
    }

    private fun navigateToTaskDetail(params: TaskNavigationParams) {
        val taskId = params.taskId
        val action = HomeFragmentDirections.actionHomeFragmentToTaskDetailFragment(taskId)
        action.taskId = taskId

        findNavController().navigate(action)
    }

}