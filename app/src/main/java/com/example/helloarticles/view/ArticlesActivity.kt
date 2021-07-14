package com.example.helloarticles.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloarticles.databinding.ActivityArticlesBinding
import com.example.helloarticles.utils.Results
import com.example.helloarticles.viewmodel.ArticleViewModel
import com.example.helloarticles.viewmodel.ArticleViewModelFactory

class ArticlesActivity : AppCompatActivity() {
    private var _binding : ActivityArticlesBinding?=null
    private val binding get() = _binding!!
    private lateinit var articlesAdapter: ArticlesAdapter
    lateinit var articleViewModel: ArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setAdapter()
        fetchRetroInfo()

    }

    private fun setAdapter() {
        articlesAdapter= ArticlesAdapter(this)
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(this@ArticlesActivity)
            addItemDecoration(DividerItemDecoration(this@ArticlesActivity, DividerItemDecoration.VERTICAL))
            adapter = articlesAdapter
        }
    }

    private fun fetchRetroInfo() {
        articleViewModel.getAllPosts().observe(this, Observer {
            when(it){
                is Results.Success->{
                    articlesAdapter?.addData(it.data)
                }
                is Results.InProgress->{
                    // TODO handle progressbar
                }
                is Results.Error->{
                    showToastMessage("Error occured :${it.exception.stackTrace}")
                }
            }
        })
    }

    private fun showToastMessage(mesg: String) {
     Toast.makeText(this,mesg,Toast.LENGTH_SHORT).show()
    }

    private fun initViewModel() {
        var retroViewModelFactory = ArticleViewModelFactory()
        articleViewModel = ViewModelProvider(this,retroViewModelFactory).get(ArticleViewModel::class.java)
        articleViewModel.fetchPostInfoFromRepository()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}