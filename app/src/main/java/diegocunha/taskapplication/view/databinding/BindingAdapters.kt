package diegocunha.taskapplication.view.databinding

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("visibleOrGone")
fun visibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("visible")
fun visible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context)
                .load(it)
                .into(view)
    }
}

@BindingAdapter("circularImageUrl")
fun loadCircularImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context)
                .load(it)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
    }
}