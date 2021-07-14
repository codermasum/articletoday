package com.example.helloarticles.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloarticles.model.network.ArticleRepository
import com.example.helloarticles.model.reponse.Post
import com.example.helloarticles.utils.Results
import kotlinx.coroutines.launch

class ArticleViewModel(articleRepository: ArticleRepository): ViewModel() {

    var articleRepository: ArticleRepository
    private val postList = MutableLiveData<Results<ArrayList<Post>>>()
    fun getAllPosts() = postList

    init {
        this.articleRepository  = articleRepository
    }

    fun fetchPostInfoFromRepository(){
        postList.value=Results.InProgress
        viewModelScope.launch{
          val response = articleRepository.fetchPostInfoList()
            response.let {
                when(it){
                    is Results.Success->{
                        postList.value=it
                    }
                }
            }
        }
    }


}