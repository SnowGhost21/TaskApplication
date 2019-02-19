package diegocunha.taskapplication.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import diegocunha.taskapplication.R
import diegocunha.taskapplication.databinding.FragmentTaskDetailBinding
import diegocunha.taskapplication.view.MainActivity
import diegocunha.taskapplication.view.comment.CommentAdapter
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

        val adapter = CommentAdapter()
        binding.commentsRecyclerView.isNestedScrollingEnabled = false
        binding.commentsRecyclerView.adapter = adapter

        viewModel.comments.observe(this, Observer {
            it?.let { comments -> adapter.setItems(comments) }
        })

        val fragmentMap = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        fragmentMap?.getMapAsync { map ->
            configureMap(map)

            viewModel.position.observe(this, Observer { position ->
                val markerOptions = createMarkerOptions(position)
                map.addMarker(markerOptions)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 16f))
            })
        }

        return binding.root

    }


    private fun configureMap(map: GoogleMap) {
        map.uiSettings.isScrollGesturesEnabled = false
        map.uiSettings.isZoomControlsEnabled = false
        map.uiSettings.isZoomGesturesEnabled = false
        map.uiSettings.isRotateGesturesEnabled = false
        map.uiSettings.isMyLocationButtonEnabled = false
    }

    private fun createMarkerOptions(position: LatLng): MarkerOptions {
        return MarkerOptions()
                .position(position)

    }

}