package com.example.helloarticles.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.helloarticles.databinding.ArticleItemBinding
import com.example.helloarticles.model.reponse.Post

class ArticlesAdapter( var context: Context):RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>(){

    private  var list: List<Post> = emptyList<Post>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.tvArticleTitle.text = this.title
                binding.tvSubTitle.text = this.published_date
                binding.root.setOnClickListener{
                    var detailIntent = Intent(context,DetailsActivity::class.java)
                    detailIntent.putExtra("url",this.url)
                    context.startActivity(detailIntent)
                }
            }
        }
    }
    fun addData(list: ArrayList<Post>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return list.size
    }

    inner  class ArticleViewHolder(val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root)
}