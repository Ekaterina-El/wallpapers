package el.ka.wallpapers.adapter

import android.util.Log
import android.view.*
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import el.ka.wallpapers.R
import el.ka.wallpapers.models.Post
import el.ka.wallpapers.utils.downloadAndSetImage
import kotlinx.android.synthetic.main.post_item.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var post: Post

    fun setPostInfo(post: Post) {
        this.post = post

        itemView.post_more.setOnClickListener { showPopup(it!!) }
        updateUI()
    }

    private fun showPopup(v: View) {
        val popup = PopupMenu(itemView.context, v)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.post_context_menu, popup.menu);
        popup.setOnMenuItemClickListener { menuItem -> selectedPopupMenu(menuItem)}
        popup.show()
    }

    private fun updateUI() {
        itemView.post_image.downloadAndSetImage(post.photoUrl)
    }


    private fun selectedPopupMenu(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.cm_download -> {
                Log.d("Download started!", "URL: ${post.photoUrl}")
            }
        }
        return true
    }

}
