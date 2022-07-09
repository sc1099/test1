package com.example.mylibrary.mypaging3.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mylibrary.mypaging3.api.GitHubService
import com.example.mylibrary.mypaging3.bean.Repo

//todo key表示页数，也就是第几页   value表示每一项数据（注意不是每一页）的内容，这里是Repo
class RepoPagingSource(private val gitHubService: GitHubService):PagingSource<Int,Repo>() {
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
      return  try {
            //获取当前页，没有就设为1
            val page = params.key?:1
            //表示每一页包含多少条数据
            val pageSize = params.loadSize
            //todo 获取服务端 当前页的数据
            val repoResponse  = gitHubService.searchRepos(page, pageSize)
            val items = repoResponse.items

            //如果当前页已经是第一页或最后一页，那么它的上一页或下一页就为null。
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (items.isNotEmpty()) page + 1 else null

          LoadResult.Page(items,prevKey,nextKey)

        }catch (e:Exception){

          LoadResult.Error(e)

        }



    }


}