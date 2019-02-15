package diegocunha.taskapplication.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import diegocunha.taskapplication.databinding.FragmentTaskDetailBinding
import diegocunha.taskapplication.view.MainActivity
import org.koin.android.ext.android.inject

class TaskDetailFragment : Fragment() {

    private val factory: TaskDetailViewModel.Factory by inject()
    private val viewModel: TaskDetailViewModel by lazy {
        ViewModelProviders.of(this, factory)
                .get(TaskDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val taskId = TaskDetailFragmentArgs.fromBundle(arguments ?: Bundle()).taskId
        factory.setTaskId(taskId)
        binding.viewModel = viewModel

        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = taskId
        }


        return binding.root

    }
}