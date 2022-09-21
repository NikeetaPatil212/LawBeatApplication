package com.example.lawbeatapplication.dataclass

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lawbeatapplication.api.ApiInterface

class PagingResource(val tabid: Int) : PagingSource<Int, NewsData>() {
    private val retrofitService by lazy {
        ApiInterface.getInstance().create(ApiInterface::class.java)
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, NewsData> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = retrofitService.getNews(params.loadSize, nextPageNumber, tid = tabid)
            return LoadResult.Page(
                data = response.body()?.data ?: listOf(),
                prevKey = null,
                nextPage(response.body()?.pager?.first())
            )
        } catch (e: Exception) {
            return LoadResult.Error(
                Exception("Network error")
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsData>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.nextKey
        }
    }

    fun nextPage(response: PagerResponse?): Int? {
        val currentpage = response?.current_page?.toInt()
        val totalpage = response?.total_pages?.toInt()
        var nextpage: Int? = null
        if (currentpage != null) {
            if (totalpage != null) {
                if ((currentpage + 1) <= totalpage)
                    nextpage = currentpage + 1
            }
        }
        return nextpage
    }
}