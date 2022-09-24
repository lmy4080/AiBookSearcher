package com.aibooksearcher.di

import android.content.Context
import com.aibooksearcher.constant.NetworkConstant
import com.aibooksearcher.data.datasource.ApiDataSource
import com.aibooksearcher.data.ApiService
import com.aibooksearcher.data.datasource.ApiDataSourceImpl
import com.aibooksearcher.data.local.dao.BookDao
import com.aibooksearcher.data.repository.ApiRepository
import com.aibooksearcher.data.repository.ApiRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstant.SERVER_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        with(OkHttpClient().newBuilder()) {
            cache(Cache(context.cacheDir, (5 * 1024 * 1024).toLong()))
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .addHeader("X-Naver-Client-Id", "UadtCUNNuCZZ8hP74pPS")
                        .addHeader("X-Naver-Client-Secret", "IuL7zJMkHH")
                        .method(it.request().method(), it.request().body())
                        .build()
                )
            }
            addInterceptor(httpLoggingInterceptor)
            return build()
        }
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiDataSource(apiService: ApiService): ApiDataSource {
        return ApiDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideApiRepository(
        apiDataSource: ApiDataSource,
        bookDao: BookDao
    ): ApiRepository {
        return ApiRepositoryImpl(apiDataSource, bookDao)
    }
}