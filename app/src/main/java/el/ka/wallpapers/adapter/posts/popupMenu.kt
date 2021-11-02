package el.ka.wallpapers.adapter.posts

import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import el.ka.wallpapers.R
import el.ka.wallpapers.models.Post
import el.ka.wallpapers.utils.APP_ACTIVITY
import el.ka.wallpapers.utils.WRITE_EXTERNAL_STORAGE
import el.ka.wallpapers.utils.checkPermission
import el.ka.wallpapers.utils.toBitmap
import kotlinx.coroutines.*
import java.net.URL

fun showPopup(v: View, post: Post) {
    val popup = PopupMenu(APP_ACTIVITY, v)
    val inflater = popup.menuInflater
    inflater.inflate(R.menu.post_context_menu, popup.menu)
    popup.setOnMenuItemClickListener { menuItem -> selectedPopupMenu(menuItem, post) }
    popup.show()
}

fun selectedPopupMenu(item: MenuItem?, post: Post): Boolean {
    when (item!!.itemId) {
        R.id.cm_download -> {
            downloadImageToGallery(post.photoUrl) {}
        }

        R.id.set_wallpaper -> {
            setWallpaper(post)
        }
    }
    return true
}

fun setWallpaper(post: Post) {
    downloadImageToGallery(post.photoUrl) { uriString ->
        val wallpaperManager = WallpaperManager.getInstance(APP_ACTIVITY)
        Toast.makeText(APP_ACTIVITY, "URI: $uriString", Toast.LENGTH_LONG).show()
        APP_ACTIVITY.startActivity(
            Intent(
                wallpaperManager.getCropAndSetWallpaperIntent(
                    Uri.parse(
                        uriString
                    )
                )
            )
        )
    }
}

fun downloadImageToGallery(photoUrl: String, onSuccess: (String) -> Unit) {
    if (checkPermission(WRITE_EXTERNAL_STORAGE)) {

        val urlImage = URL(photoUrl)
        val result: Deferred<Bitmap?> = GlobalScope.async {
            urlImage.toBitmap()
        }

        GlobalScope.launch(Dispatchers.Main) {
            val bitmap: Bitmap? = result.await()
            val uri =
                MediaStore.Images.Media.insertImage(APP_ACTIVITY.contentResolver, bitmap, "", "")
            onSuccess(uri)
        }
    }
}
