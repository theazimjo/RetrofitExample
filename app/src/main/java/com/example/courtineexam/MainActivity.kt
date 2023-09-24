package com.example.courtineexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.courtineexam.databinding.ActivityMainBinding
import com.example.retrofit.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var TAG = "TAG"
    private lateinit var userAdapter: UserAdapter
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter()
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in").build()

        val api = retrofit.create(ApiInterface::class.java)

        userAdapter = UserAdapter()
        val manager = LinearLayoutManager(this@MainActivity)
        binding.recyclearview.apply {
            adapter = userAdapter
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(this@MainActivity, manager.orientation))
        }


        val flow: Flow<PagingData<User>> =
            Pager(config = PagingConfig(pageSize = 3, enablePlaceholders = false),
                pagingSourceFactory = { UserPagingSourse(api) }).flow


        lifecycleScope.launch {
            flow.collect {
                userAdapter.submitData(it)
            }
        }
        lifecycleScope.launch {
            userAdapter.loadStateFlow.collect {
                binding.progressHorizontal.isVisible = it.source.append is LoadState.Loading
            }
        }
    }
}