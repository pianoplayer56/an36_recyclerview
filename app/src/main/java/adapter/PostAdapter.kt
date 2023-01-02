package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firstandroidapp.R
import com.example.firstandroidapp.databinding.CardPostBinding
import ru.netology.data.Post
import ru.netology.viewmodel.getRightNumber


typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) :
    ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallBack()) {


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }

    class PostViewHolder(
        private val binding: CardPostBinding,
        private val onLikeListener: OnLikeListener,
        private val onShareListener: OnShareListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.apply {
                author.text = post.author
                published.text = post.published
                likesNumber.text = getRightNumber(post.likes)
                repostsNumber.text = getRightNumber(post.reposts)
                viewsNumber.text = getRightNumber(post.views)
                paragraph1.text = post.text

                like.setImageResource(
                    if (post.isLiked) R.drawable.liked else R.drawable.like
                )

                reposts.setImageResource(if (post.isReposted) R.drawable.reposts else R.drawable.reposts)

                reposts.setOnClickListener {
                    onShareListener(post)
                }

                like.setOnClickListener {
                    onLikeListener(post)
                }

            }
        }
    }
}