package com.example.mylibrary.mypaging3.bean

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mylibrary.mypaging3.api.GitHubService
import com.example.mylibrary.mypaging3.paging.RepoPagingSource
import kotlinx.coroutines.flow.Flow


// TODO: 一种固定的写法,除了Repo部分是可以改的，其他部分都是固定的。 
//创建了一个Pager对象，并调用.flow将它转换成一个Flow对象
object Repository {

    private const val PAGE_SIZE = 10

    private val gitHubService = GitHubService.create()

    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { RepoPagingSource(gitHubService) }
        ).flow
    }

}

