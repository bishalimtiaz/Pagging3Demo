package com.example.pagging3demo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pagging3demo.data.local.UnsplashDatabase
import com.example.pagging3demo.data.paging.SearchPagingSource
import com.example.pagging3demo.data.paging.UnsplashRemoteMediator
import com.example.pagging3demo.data.remote.UnSplashApi
import com.example.pagging3demo.model.UnsplashImage
import com.example.pagging3demo.utils.Constants.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

@ExperimentalPagingApi
class UnsplashRepository @Inject constructor(
    private val unSplashApi: UnSplashApi,
    private val unsplashDatabase: UnsplashDatabase
) {
    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unSplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchAllImages(query: String): Flow<PagingData<UnsplashImage>>{
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = unSplashApi, query = query)
            }
        ).flow
    }
}