package com.home24.articlefeature.ui

import android.app.Application
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.home24.articlefeature.viewmodel.ArticleViewModel
import com.home24.articlefeature.repository.ArticleUseCase
import com.home24.R
import com.home24.data.table.*
import com.home24.matcher.RecyclerViewMatcher
import org.hamcrest.core.StringContains.containsString
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
open class ArticleFragmentTest: KoinTest {

    private lateinit var articleViewModel: ArticleViewModel
    @Mock
    private lateinit var articleUseCase: ArticleUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        articleViewModel = ArticleViewModel(Application(), articleUseCase)
        loadKoinModules(module {
            viewModel {
                articleViewModel
            }
        })
    }

    @Test
    fun launchFragmentAndVerifyArticleCounter() {

        val scenario = launchFragmentInContainer<ArticleFragment>(themeResId = R.style.AppTheme)
        scenario.onFragment {

            articleViewModel.likeArticleNumber.postValue(0)
        }

        onView(withId(R.id.tvArticleCounter)).check(matches(withText(containsString(articleViewModel.likeArticleNumber.value?.toString()))))
    }

    @Test
    fun launchFragmentAndVerifyDataPopulation() {

        val scenario = launchFragmentInContainer<ArticleFragment>(themeResId = R.style.AppTheme)
        scenario.onFragment {

            articleViewModel.articlesList.postValue(listOf<Articles>(Articles(1,"Premium Komfortmatratze Smood", "",Price(0.00,"",false),"",Price(0.00,"",false),
                Delivery(Time("",0,""),"","","","","", emptyList()),
                Brand("","", emptyList(),""), emptyList(),"","","","",0,Reviews(0,0),false, emptyList(), emptyList<Showrooms>(),
                Metadata(""),Links("", Self(""))
            )))
        }

        onView(RecyclerViewMatcher(R.id.articlesRecyclerView).atPosition(0))
            .check(matches(ViewMatchers.hasDescendant(withText("Premium Komfortmatratze Smood"))))

    }

    @After
    fun cleanUp() {
        stopKoin()
    }
}