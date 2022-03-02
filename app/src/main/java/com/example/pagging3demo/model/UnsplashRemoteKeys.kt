package com.example.pagging3demo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagging3demo.utils.Constants.UNSPLASH_REMOTE_KEYS_TABLE

/**
 * Purpose this table is to store prevPage and nextPage keys in local DB
 * so that remoteMediator knows which page to request next
 * **/

@Entity(tableName = UNSPLASH_REMOTE_KEYS_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
