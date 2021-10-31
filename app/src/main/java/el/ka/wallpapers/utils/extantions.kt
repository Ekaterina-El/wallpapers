package el.ka.wallpapers.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import el.ka.wallpapers.R

fun ImageView.downloadAndSetImage(photoUrl: String) {
    Picasso.get()
        .load(photoUrl)
        .fit()
        .placeholder(R.drawable.placeholder)
        .into(this)
}