package ro.emag.fashiondays

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("image")
fun ImageView.loadImage(url: String?) {
    url?.let {
        load(it) {
            allowHardware(false)
            crossfade(true)
        }
    }
}