package com.example.myapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Post
import com.example.myapplication.databinding.RowLayoutBinding

class PostsAdapter(var context: Context, var posts: List<Post>) :
    RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindata(post: Post) {
            binding.tvId.text = post.id.toString()
            binding.tvUserid.text = post.userId.toString()
            binding.tvbody.text = post.body.toString()
            binding.tvtitle.text = post.title.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var post = posts[position]
        holder.bindata(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

}