package com.example.pandasoft

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pandasoft.ui.news.model.DataItem
import com.example.pandasoft.ui.news.page.newList.NewListFragment
import com.example.pandasoft.ui.news.page.newList.NewListViewModel
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class NewListFragmentTest {

    private lateinit var rootView: View
    private lateinit var viewModel: NewListViewModel
    private lateinit var mockAdapter: NewListFragment.RecyclerViewAdapter

    private val repoFragment = NewListFragmentTest()


}