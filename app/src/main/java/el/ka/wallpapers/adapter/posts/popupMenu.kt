package el.ka.wallpapers.adapter.posts

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import el.ka.wallpapers.R
import el.ka.wallpapers.models.Post
import el.ka.wallpapers.utils.WRITE_EXTERNAL_STORAGE
import el.ka.wallpapers.utils.checkPermission
import el.ka.wallpapers.utils.toBitmap
import kotlinx.coroutines.*
import java.net.URL

fun showPopup(v: View, context: Context, post: Post) {
    val popup = PopupMenu(context, v)
    val inflater = popup.menuInflater
    inflater.inflate(R.menu.post_context_menu, popup.menu)
    popup.setOnMenuItemClickListener { menuItem -> selectedPopupMenu(menuItem, post, context) }
    popup.show()
}

fun selectedPopupMenu(item: MenuItem?, post: Post, context: Context): Boolean {
    when (item!!.itemId) {
        R.id.cm_download -> {
            if (checkPermission(WRITE_EXTERNAL_STORAGE)) {
                downloadImageToGallery(post.photoUrl, context.contentResolver)
            }
        }
    }
    return true
}

fun downloadImageToGallery(photoUrl: String, cr: ContentResolver) {
    val urlImage = URL(photoUrl)
    val result: Deferred<Bitmap?> = GlobalScope.async {
        urlImage.toBitmap()
    }

    GlobalScope.launch(Dispatchers.Main) {
        val bitmap: Bitmap? = result.await()

        var savedImageURL = MediaStore.Images.Media.insertImage(
            cr,
            bitmap,
            "Wallpaper",
            "Image of wallpaper"
        )

        Log.d("Download images", "savedImageURL: $savedImageURL");
    }
}
