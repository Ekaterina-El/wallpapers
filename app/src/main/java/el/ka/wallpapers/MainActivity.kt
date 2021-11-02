package el.ka.wallpapers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import el.ka.wallpapers.adapter.posts.PostsAdapter
import el.ka.wallpapers.temp.posts
import el.ka.wallpapers.utils.APP_ACTIVITY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        APP_ACTIVITY = this

        postsAdapter = PostsAdapter()
        postsAdapter.addItem(posts[0])
        postsAdapter.addItem(posts[1])
        postsAdapter.addItem(posts[2])

        posts_list.adapter = postsAdapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}