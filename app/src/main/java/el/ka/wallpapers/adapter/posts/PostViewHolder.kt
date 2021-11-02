package el.ka.wallpapers.adapter.posts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import el.ka.wallpapers.models.Post
import el.ka.wallpapers.utils.downloadAndSetImage
import kotlinx.android.synthetic.main.post_item.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var post: Post

    fun setPostInfo(post: Post) {
        this.post = post

        itemView.post_more.setOnClickListener { showPopup(it!!, post) }
        updateUI()
    }

    private fun updateUI() {
        itemView.post_image.downloadAndSetImage(post.photoUrl)
    }

}

