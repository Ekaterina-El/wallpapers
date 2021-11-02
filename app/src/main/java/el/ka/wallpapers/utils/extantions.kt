package el.ka.wallpapers.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.squareup.picasso.Picasso
import el.ka.wallpapers.R
import java.io.IOException
import java.net.URL

fun ImageView.downloadAndSetImage(photoUrl: String) {
    Picasso.get()
        .load(photoUrl)
        .fit()
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun URL.toBitmap(): Bitmap? {
    return try {
        BitmapFactory.decodeStream(openStream())
    } catch (e: IOException) {
        null
    }
}