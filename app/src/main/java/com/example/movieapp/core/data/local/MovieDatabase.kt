package com.example.movieapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.core.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase:RoomDatabase() {

    abstract val dao: MovieDao
}