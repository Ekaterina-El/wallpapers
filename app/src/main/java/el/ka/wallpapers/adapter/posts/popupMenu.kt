package el.ka.wallpapers.adapter.posts

import android.content.Context
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import el.ka.wallpapers.R
import el.ka.wallpapers.models.Post

fun showPopup(v: View, context: Context, post: Post) {
    val popup = PopupMenu(context, v)
    val inflater = popup.menuInflater
    inflater.inflate(R.menu.post_context_menu, popup.menu)
    popup.setOnMenuItemClickListener { menuItem -> selectedPopupMenu(menuItem, post) }
    popup.show()
}

fun selectedPopupMenu(item: MenuItem?, post: Post): Boolean {
    when (item!!.itemId) {
        R.id.cm_download -> {
            Log.d("Download started!", "URL: ${post.photoUrl}")
        }
    }
    return true
}
