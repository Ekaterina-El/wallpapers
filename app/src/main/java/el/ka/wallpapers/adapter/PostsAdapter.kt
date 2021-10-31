package el.ka.wallpapers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import el.ka.wallpapers.R
import el.ka.wallpapers.models.Post

class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {
    private val posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.setPostInfo(posts[position])
    }

    override fun getItemCount() = posts.size

    fun addItem(post: Post) {
        posts.add(0, post)
        notifyItemInserted(0)
    }
}