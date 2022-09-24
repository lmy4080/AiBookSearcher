package com.aibooksearcher.di

import android.app.Application
import androidx.room.Room
import com.aibooksearcher.data.local.AppDatabase
import com.aibooksearcher.data.local.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// 의존성 주입
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideBookDao(appDatabase: AppDatabase): BookDao {
        return appDatabase.bookDao
    }
}