package com.example.technical_test_novian.di

import com.example.technical_test_novian.common.network.ApiInterceptor
import com.example.technical_test_novian.utils.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

private const val timeoutRead = 30L   //In seconds
private const val timeoutConnect = 30L   //In seconds

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiInterceptor(): ApiInterceptor = ApiInterceptor()


    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideOkHttpClient(
        apiInterceptor: ApiInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
//            .addInterceptor(apiInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(timeoutConnect, TimeUnit.SECONDS)
            .readTimeout(timeoutRead, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Named("IO")
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}