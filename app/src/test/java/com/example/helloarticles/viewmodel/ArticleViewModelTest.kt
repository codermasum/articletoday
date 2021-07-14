package com.example.helloarticles.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.helloarticles.TestCoroutineRule
import com.example.helloarticles.model.network.ArticleRepository
import com.example.helloarticles.model.reponse.Post
import com.example.helloarticles.utils.Results
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import javax.inject.Inject
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class ArticleViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var articleViewModel: ArticleViewModel
    @Mock
    private lateinit var articleRepository: ArticleRepository
    @Mock
    private lateinit var articleResponseObserver: Observer<Results<ArrayList<Post>>>
    @Before
    fun setUp() {
        articleViewModel = ArticleViewModel(articleRepository)
    }
    @Test
    fun `when fetching results ok then return a list successfully`(){
        val emptyList = arrayListOf<Post>()
        testCoroutineRule.runBlockingTest {
            articleViewModel.getAllPosts().observeForever(articleResponseObserver)
            whenever(articleRepository.fetchPostInfoList()).thenAnswer {
                Results.Success(emptyList)
            }
            articleViewModel.fetchPostInfoFromRepository()
            assertNotNull(articleViewModel.getAllPosts().getOrAwaitValue())
            assertEquals(Results.Success(emptyList), articleViewModel.getAllPosts().getOrAwaitValue())
        }
    }

    @After
    fun tearDown() {
       articleViewModel.getAllPosts().removeObserver(articleResponseObserver)
    }
}